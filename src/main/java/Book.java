public class Book {

    private String title; //setting variables for each changable part of the app
    private String author;
    private int rating;
    private String review;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Book(String title, String author, int rating, String review){
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.review = review;
    }

    @Override
    public String toString() { //shows up in the standard GUI form
        return "REVIEW FOR: " +
                "Book Title: '" + title + '\'' +
                " Author: '" + author + '\'';

    }


    public String toStringTwo() { //shows up in the "See Reviews" dialog tab
        return  "Book Title: '" + title + "'\n" +
                "Author: '" + author + "'\n" +
                "Rating (Out of 5): '" + rating + "'\n" +
                "Review: '" + review + "'\n";
    }
}
