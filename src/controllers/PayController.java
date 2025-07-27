package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class PayController {
    private view.PayView payView;
    private models.RegisteredUser registeredUser;
    private models.Pricing pricingModel;
    public String lastMessage;

    public PayController(view.PayView payView, models.RegisteredUser registeredUser) {
        this.payView = payView;
        this.registeredUser = registeredUser;
        this.pricingModel = new models.Pricing();
        this.payView.updateBalance(registeredUser);
        this.payView.updatePrice(registeredUser, pricingModel);

        this.payView.payButton.addActionListener(e -> confirmPayment());
        this.payView.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payView.dispose();
                view.FeedView feedView = new view.FeedView();
                new controllers.FeedController(feedView, registeredUser);
                feedView.setVisible(true);
            }
        });
    }
    
    private void confirmPayment() {
        double totalAmount = Double.parseDouble(this.payView.priceValueLabel.getText().replace("Bs. ", ""));
        if (registeredUser.getBalance() < totalAmount) {
            JOptionPane.showMessageDialog(null,
                    "Saldo insuficiente para realizar el pago.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Saldo insuficiente para realizar el pago.";
            return;
        }
        String userImagePath = payView.imagePathField.getText();
        if (userImagePath == null || userImagePath.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Debe subir una imagen para el reconocimiento facial.",
                "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Debe subir una imagen para el reconocimiento facial.";
            return;
        }
        int faceResult = compareUserFace(userImagePath, registeredUser.getUser());
        if (faceResult == -1) {
            JOptionPane.showMessageDialog(null,
                "No se encontró la imagen almacenada del usuario (ni PNG ni JPG).",
                "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "No se encontró la imagen almacenada del usuario.";
            return;
        }
        if (faceResult == 0) {
            JOptionPane.showMessageDialog(null,
                "Reconocimiento facial fallido.",
                "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Reconocimiento facial fallido.";
            return;
        }
        registeredUser.addBalance(-totalAmount);
        payView.updateBalance(registeredUser);
        JOptionPane.showMessageDialog(null,
                "Pago realizado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        lastMessage = "Pago realizado con éxito.";
        payView.dispose();
        view.FeedView feedView = new view.FeedView();
        new controllers.FeedController(feedView, registeredUser);
        feedView.setVisible(true);
    }

    public int compareUserFace(String userImagePath, String cedula) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier faceDetector = new CascadeClassifier("lib/haarcascade_frontalface_default.xml");

        // Cargar imagen proporcionada por el usuario
        Mat userImg = Imgcodecs.imread(userImagePath);
        // Cargar imagen almacenada
        String[] extensions = {".png", ".jpg"};
        Mat storedImg = null;
        boolean foundImage = false;
        for (String ext : extensions) {
            String path = "src/models/data/images/" + cedula + ext;
            storedImg = Imgcodecs.imread(path);
            if (!storedImg.empty()) {
                foundImage = true;
                break;
            }
        }
        if (!foundImage) return -1; // No se encontró ninguna imagen
        if (userImg.empty() || storedImg == null || storedImg.empty()) return 0;

        // Detectar rostros en ambas imágenes
        MatOfRect userFaces = new MatOfRect();
        MatOfRect storedFaces = new MatOfRect();
        faceDetector.detectMultiScale(userImg, userFaces);
        faceDetector.detectMultiScale(storedImg, storedFaces);

        Rect[] userRects = userFaces.toArray();
        Rect[] storedRects = storedFaces.toArray();
        if (userRects.length == 0 || storedRects.length == 0) return 0;

        // Recorta el primer rostro encontrado en cada imagen
        Mat userFace = new Mat(userImg, userRects[0]);
        Mat storedFace = new Mat(storedImg, storedRects[0]);

        // Convierte a escala de grises
        org.opencv.imgproc.Imgproc.cvtColor(userFace, userFace, org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY);
        org.opencv.imgproc.Imgproc.cvtColor(storedFace, storedFace, org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY);

        // Calcula histogramas
        Mat userHist = new Mat();
        Mat storedHist = new Mat();
        org.opencv.imgproc.Imgproc.calcHist(java.util.Arrays.asList(userFace), new MatOfInt(0), new Mat(), userHist, new MatOfInt(256), new MatOfFloat(0, 256));
        org.opencv.imgproc.Imgproc.calcHist(java.util.Arrays.asList(storedFace), new MatOfInt(0), new Mat(), storedHist, new MatOfInt(256), new MatOfFloat(0, 256));

        // Compara los histogramas (correlación)
        double similarity = org.opencv.imgproc.Imgproc.compareHist(userHist, storedHist, org.opencv.imgproc.Imgproc.CV_COMP_CORREL);

        // Considera similar si la correlación es mayor a 0.8
        return similarity > 0.5 ? 1 : 0;
    }
}
