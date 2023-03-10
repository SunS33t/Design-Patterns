package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML private TableView<Coordinate> tableView;
    @FXML private TableColumn<Coordinate, Double> xColumn;
    @FXML private TableColumn<Coordinate,Double> yColumn;
    @FXML private LineChart<Number,Number> lineChart;
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private TextField numberInput;
    private Comparator<Coordinate> comparator = Comparator.comparingDouble(Coordinate::getX);

    private ObservableList<Coordinate> coordinates = FXCollections.observableArrayList();
    private XYChart.Series<Number, Number> series = new XYChart.Series<>();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        lineChart.getData().add(series);

        coordinates.addListener((ListChangeListener<Coordinate>) c -> {
            series = new XYChart.Series<>();
            while (c.next()) {
                if (c.wasAdded() || c.wasUpdated() || c.wasPermutated()) {
                    coordinates.forEach(cord-> {
                        series.getData().add(new XYChart.Data(cord.getX(),cord.getY()));
                    });
                    lineChart.getData().remove(0);
                    lineChart.getData().add(series);
                    System.out.println();
                }
            }
        });

        xColumn.setCellValueFactory(new PropertyValueFactory("X"));
        yColumn.setCellValueFactory(new PropertyValueFactory("Y"));

        tableView.setItems(coordinates);

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn((new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                return aDouble.toString();
            }

            @Override
            public Double fromString(String s) {
                double num = 0;
                try{
                    num = Double.parseDouble(s);
                }catch (Exception e){
                    throw new NumberFormatException();
                }
                finally {
                    return num;
                }
            }
        })));

        coordinates.add(new Coordinate(1));
        coordinates.add(new Coordinate(2));
        coordinates.add(new Coordinate(4));
        coordinates.add(new Coordinate(8));
    }

    public void changeXCellEvent(TableColumn.CellEditEvent editedCell){
        double value = Double.parseDouble(editedCell.getOldValue().toString());
        try{
            value = Double.parseDouble(editedCell.getNewValue().toString());
        }
        catch (NumberFormatException e){
            System.out.println("Error");
        }
        coordinates.set(tableView.getSelectionModel().getSelectedIndex(), new Coordinate(value));
        coordinates.sort(comparator);
        tableView.refresh();
    }

    public void addButtonPushed(){
        try {
            Coordinate cord = new Coordinate(Double.parseDouble(numberInput.getText()));
            System.out.println("value entered");
        }
        catch (Exception e){

        }
    }

    public void onKeyPressed(KeyEvent value){
        if(Character.isDigit(Integer.parseInt(value.getCharacter()))){

        }
    }

}