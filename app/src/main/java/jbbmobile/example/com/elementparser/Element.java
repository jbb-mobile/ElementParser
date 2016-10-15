package jbbmobile.example.com.elementparser;

import android.util.Pair;

public class Element {

    private int idElement;
    private String nameElement;
    private Pair<Double, Double> coordinates;
    private String defaultImage;
    private String textDescription;

    public Element(){
    }

    public Element(int idElement, Pair<Double, Double> coordinates, String defaultImage, String nameElement, String textDescription){
        validateID(idElement);
        validateDefaultImage(defaultImage);
        validateNameElement(nameElement);
        validateTextDescription(textDescription);
        validateCoordinates(coordinates);
    }

    public Element(int idElement, String nameElement, String textDescription, Pair<Double, Double> coordinates) {
        validateID(idElement);
        validateNameElement(nameElement);
        validateTextDescription(textDescription);
        validateCoordinates(coordinates);
    }

    private void validateID(int idElement){
        if(idElement < 0 || idElement > 100)
            throw new IllegalArgumentException("Invalid element's id");
        else
            setIdElement(idElement);
    }

    private void validateCoordinates(Pair<Double, Double> coordinates){
        if(coordinates.first == null || coordinates.second == null)
            throw new IllegalArgumentException("Invalid element's coordinate");
        else
            setDefaultImage(defaultImage);
    }

    private void validateDefaultImage(String defaultImage){
        if(defaultImage == null)
            throw new IllegalArgumentException("Invalid element's default image");
        else
            setDefaultImage(defaultImage);
    }

    private void validateNameElement(String nameElement){
        if(nameElement == null || nameElement.length() > 80)
            throw new IllegalArgumentException("Invalid element's name");
        else
            setNameElement(nameElement);
    }

    private void validateTextDescription(String textDescription){
        if(textDescription == null)
            throw new IllegalArgumentException("Invalid element's description");
        else
            setTextDescription(textDescription);
    }

    public Pair<Double, Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getNameElement() {
        return nameElement;
    }

    public void setNameElement(String nameElement) {
        this.nameElement = nameElement;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }
}

