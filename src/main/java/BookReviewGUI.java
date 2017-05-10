import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookReviewGUI extends JFrame{
    private JPanel rootPanel;
    private JButton createButton;
    private JTextField bookTitleText;
    private JTextField bookAuthorText;
    private JComboBox bookRatingBox;
    private JTextArea bookReviewText;
    private JButton seeReviewButton;
    private JButton quitButton;
    private JList<Book> reviewList;
    private JButton deleteButton;
    private static ArrayList<Book> dbBook = dbController.loadBook();
    private DefaultListModel<Book> listModel;

    protected BookReviewGUI() {

        try {
            setContentPane(rootPanel);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
            setSize(500,600); //custom GUI size

            bookRatingBox.addItem(1); //setting number assets for rating combobox from 1-5
            bookRatingBox.addItem(2);
            bookRatingBox.addItem(3);
            bookRatingBox.addItem(4);
            bookRatingBox.addItem(5);

            listModel = new DefaultListModel<>();
            reviewList.setModel(listModel);
            reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //allows 1 selection at a time in JList

            try {
                for (Book book : dbBook) {

                    if (book != null) {

                        listModel.addElement(book); //loads any review from db into JList
                    }
                }
            }catch (NullPointerException npe) {
                npe.printStackTrace();
                JOptionPane.showMessageDialog(BookReviewGUI.this, "Null Error");
            }
        }catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        createButton.addActionListener(new ActionListener() { //on Create button press
            @Override
            public void actionPerformed(ActionEvent e) {
                String ti = bookTitleText.getText(); //gather text input from title, author, review, and rating (1-5)
                String au = bookAuthorText.getText();
                int ra = (Integer)bookRatingBox.getSelectedItem();
                String re = bookReviewText.getText();

                Book book = new Book(ti, au, ra, re);
                if (bookTitleText.getText().equals("") || bookAuthorText.getText().equals("")
                        || bookReviewText.getText().equals("")) { //if nothing entered into these textboxes, activate validation
                    Validation();
                } else {
                    dbController.newBook(ti, au, ra, re);
                    listModel.addElement(book); //if all are filled, add book review to database



                    clearText(); //remove all text from specified fields
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //quit app
            }
        });

        seeReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Book reviewBook = reviewList.getSelectedValue(); //add dialog box to show all information about review
                JOptionPane.showMessageDialog(BookReviewGUI.this, reviewBook.toStringTwo());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Book delBook = reviewList.getSelectedValue(); //gather whatever has been highlighted
                if (delBook == null){
                    JOptionPane.showMessageDialog(BookReviewGUI.this,
                            "Please select a review to delete."); //error for selecting nothing
                } else {
                    dbController.removeBook(delBook);
                    listModel.removeElement(delBook); //remove from JList & database
                    clearText();
                }
            }
        });
    }

    void Validation() {

        if (bookTitleText.getText().equals("")) { //validates if textfield is left blank, enter book title
            bookTitleText.grabFocus();
            JOptionPane.showMessageDialog(BookReviewGUI.this, "Enter a book title.");
        }
        if (bookAuthorText.getText().equals("")) { //if user doesn't know the author, there is more than could list, or none, just add 'N/A'
            bookAuthorText.grabFocus();
            JOptionPane.showMessageDialog
                    (BookReviewGUI.this, "Enter an author name. If you do not know, enter 'N/A'");
        }
        if (bookReviewText.getText().equals("")) { //same as book title, a review is not a review without a review...
            bookReviewText.grabFocus();
            JOptionPane.showMessageDialog(BookReviewGUI.this, "Enter a review.");
        }
    }

    void clearText(){
// clear everything once Create is hit
        bookTitleText.setText("");
        bookAuthorText.setText("");
        bookReviewText.setText("");
    }
}
