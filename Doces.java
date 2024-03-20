import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Doces extends JFrame implements ActionListener {
    private JPanel[] docesPanel;
    private JTextField[] quantidadeCampos;
    private JLabel[] docesLabel;
    private JLabel[] precoLabel;
    private JLabel[] Button;
    private double[] precos = {3.50, 2.75, 1.00};

    public Doces() {
        setTitle("Loja de doces da Rafa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        docesPanel = new JPanel[3];
        quantidadeCampos = new JTextField[3];
        docesLabel = new JLabel[3];
        precoLabel = new JLabel[3];

        String[] candyImagePaths = {"biscoito.jpeg", "Coracao.jpeg", "morango.jpeg"};
        for (int i = 0; i < 3; i++) {
            docesPanel[i] = new JPanel(new BorderLayout());
            try {
                BufferedImage originalImage = ImageIO.read(getClass().getResource(candyImagePaths[i]));
                BufferedImage resizedImage = resizeImage(originalImage, 100, 100); // Tamanho desejado
                ImageIcon candyIcon = new ImageIcon(resizedImage);
                docesLabel[i] = new JLabel(candyIcon);
                docesPanel[i].add(docesLabel[i], BorderLayout.CENTER);

                precoLabel[i] = new JLabel("R$" + precos[i]);
                precoLabel[i].setHorizontalAlignment(JLabel.RIGHT); // Alinhamento à direita
                docesPanel[i].add(precoLabel[i], BorderLayout.LINE_END);

                GridBagConstraints gbcCandy = new GridBagConstraints();
                gbcCandy.gridx = 0;
                gbcCandy.gridy = i + 1;
                gbcCandy.fill = GridBagConstraints.HORIZONTAL;
                gbcCandy.insets = new Insets(5, 5, 5, 5); // Espaçamento
                add(docesPanel[i], gbcCandy);

                quantidadeCampos[i] = new JTextField(5);
                GridBagConstraints gbcQuantidade= new GridBagConstraints();
                gbcQuantidade.gridx = 1;
                gbcQuantidade.gridy = i + 1;
                gbcQuantidade.insets = new Insets(5, 5, 5, 5); // Espaçamento
                add(quantidadeCampos[i], gbcQuantidade);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JButton Button = new JButton("Pedir");
        Button.addActionListener(this);
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 4;
        gbcButton.gridwidth = 2;
        gbcButton.fill = GridBagConstraints.HORIZONTAL;
        gbcButton.insets = new Insets(10, 0, 0, 0); // Espaçamento
        add(Button, gbcButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Button) {
            double total = 0;
            for (int i = 0; i < 3; i++) {
                int quantity = Integer.parseInt(quantidadeCampos[i].getText());
                total += precos[i] * quantity;
            }
            JOptionPane.showMessageDialog(this, "Total da compra: $" + total);
        }
    }

    public static void main(String[] args) {
        new Doces();
    }
}