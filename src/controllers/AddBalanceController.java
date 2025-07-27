package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AddBalanceController {
    private view.AddBalanceView addBalanceView;
    private models.RegisteredUser registeredUser;
    public String lastMessage;

    public AddBalanceController(view.AddBalanceView addBalanceView, models.RegisteredUser registeredUser) {
        this.addBalanceView = addBalanceView;
        this.registeredUser = registeredUser;
        this.addBalanceView.updateBalance(registeredUser);

        this.addBalanceView.validateButton.addActionListener(e -> saveBalance());
        this.addBalanceView.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBalanceView.dispose();
                view.FeedView feedView = new view.FeedView();
                new controllers.FeedController(feedView, registeredUser);
                feedView.setVisible(true);
            }
        });
    }


    private void saveBalance() {
        String bankCode = addBalanceView.bankCombo.getSelectedItem().toString();
        String phoneCode = addBalanceView.phoneCodeCombo.getSelectedItem().toString();
        String phone = addBalanceView.phoneField.getText().trim();
        String date = addBalanceView.dateField.getText().trim();
        String digits = addBalanceView.digitsField.getText().trim();
        if(bankCode.isEmpty() || phoneCode.isEmpty() || phone.isEmpty() || date.isEmpty() || digits.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Por favor, complete todos los campos.",
                    "Error", JOptionPane.WARNING_MESSAGE);
                lastMessage = "Por favor, complete todos los campos.";
                return;
        }
        if(!validatePhone()) {
            JOptionPane.showMessageDialog(null,
                    "Teléfono inválido. Debe tener 7 dígitos.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Teléfono inválido. Debe tener 7 dígitos.";
            return;
        }
        if(!validateDate()) {
            JOptionPane.showMessageDialog(null,
                    "Fecha inválida. Formato debe ser DD/MM/AAAA.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Fecha inválida. Formato debe ser DD/MM/AAAA.";
            return;
        }
        if(!validateDigits()) {
            JOptionPane.showMessageDialog(null,
                    "Los últimos 4 dígitos inválidos.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Los últimos 4 dígitos inválidos.";
            return;
        }
        String balance = validatePayment(bankCode, phoneCode, phone, date, digits);
        if(balance == null) {
            JOptionPane.showMessageDialog(null,
                    "Por favor verifique los detalles del pago.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Por favor verifique los detalles del pago.";
            return;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Pago validado con éxito.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            lastMessage = "Pago validado con éxito.";
            registeredUser.addBalance(Double.parseDouble(balance));
            addBalanceView.dispose();
            view.FeedView feedView = new view.FeedView();
            new controllers.FeedController(feedView, registeredUser);
            feedView.setVisible(true);
        }
    }

    private boolean validatePhone() {
        String phone = addBalanceView.phoneField.getText().trim();
        String regex = "^\\+?[0-9. ()-]{7}$";
        return phone.matches(regex);
    }

    private boolean validateDate() {
        String date = addBalanceView.dateField.getText().trim();
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        return date.matches(regex);
    }

    private boolean validateDigits() {
        String digits = addBalanceView.digitsField.getText().trim();
        String regex = "^\\d{4}$";
        return digits.matches(regex);
    }

    private String validatePayment(String bankCode, String phoneCode, String phone, String date, String digits) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        String balance = null;
        // Leer todas las líneas
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream("src/models/data/approvedPayments.txt"), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 6 &&
                    parts[0].equals(bankCode) &&
                    parts[1].equals(phoneCode) &&
                    parts[2].equals(phone) &&
                    parts[3].equals(date) &&
                    parts[4].equals(digits)) {
                    balance = parts[5]; // Coincidencia, guardar balance y no agregar la línea
                } else {
                    lines.add(line);
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        // Si se encontró, reescribir el archivo sin la línea eliminada
        if (balance != null) {
            try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream("src/models/data/approvedPayments.txt"), "UTF-8"))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return balance;
    }

}
