package com.example.houseagent.service;

import com.example.houseagent.dao.OwnerDao;
import com.example.houseagent.entity.Owner;
import com.example.houseagent.entity.Property;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.List;

// Extend the Application class
public class Owners extends Application {

    // Declare some global variables
    private TextField idField, firstNameField, lastNameField, phoneField, emailField, addressField;
    private Button addButton, editPropertiesButton, removeButton, refreshButton;
    private TableView<Owner> table;
    public OwnerDao ownerDao;
    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a label for the ID field
        Label idLabel = new Label("ID:");

        // Create a text field for the ID field
        idField = new TextField();
        idField.setEditable(false);

        // Create a label for the first name field
        Label firstNameLabel = new Label("First Name:");

        // Create a text field for the first name field
        firstNameField = new TextField();

        // Create a label for the last name field
        Label lastNameLabel = new Label("Last Name:");

        // Create a text field for the last name field
        lastNameField = new TextField();

        // Create a label for the phone field
        Label phoneLabel = new Label("Phone:");

        // Create a text field for the phone field
        phoneField = new TextField();

        // Create a label for the email field
        Label emailLabel = new Label("Email:");

        // Create a text field for the email field
        emailField = new TextField();

        // Create a label for the address field
        Label addressLabel = new Label("Address:");

        // Create a text field for the address field
        addressField = new TextField();
        //get the information from the label
        String  id = idField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        // Create buttons for adding, editing properties, removing, and refreshing owner information
        addButton = new Button("Add");
        editPropertiesButton = new Button("Edit Properties");
        removeButton = new Button("Remove");
        refreshButton = new Button("Refresh");

        addButton.setOnAction(e->{
            add(firstName, lastName, phone, email, address);
        });
        refreshButton.setOnAction(e->{
            table.getItems().clear();
            loadDataFromDatabase();
        });
        removeButton.setOnAction(e -> {
            // 获取从表格中选中的 Owner 对象
            Owner selectedOwner = table.getSelectionModel().getSelectedItem();

            if (selectedOwner != null) {
                // 删除数据库中的 Owner 记录
                removeOwner(selectedOwner);
                clearFields();
                // 刷新表格数据
                //loadDataFromDatabase();
            }
        });

        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, editPropertiesButton, removeButton, refreshButton);

        // Create a grid pane for the fields and labels
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameField, 1, 1);
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameField, 1, 2);
        gridPane.add(phoneLabel, 0, 3);
        gridPane.add(phoneField, 1, 3);
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailField, 1, 4);
        gridPane.add(addressLabel, 0, 5);
        gridPane.add(addressField, 1, 5);

        //Create a table view for displaying owner information
        table = new TableView<>();
        table.setPrefWidth(600);

        //Create table columns for each owner attribute
        TableColumn<Owner, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<Owner, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<Owner, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        TableColumn<Owner, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        TableColumn<Owner, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        //Add the columns to the table view
        table.getColumns().addAll(firstNameColumn, lastNameColumn, phoneColumn, emailColumn, addressColumn);

        editPropertiesButton.setOnAction(e -> {
            // 获取从表格中选中的 Owner 对象
            Owner selectedOwner = table.getSelectionModel().getSelectedItem();

            if (selectedOwner != null) {
                // 创建一个新的窗口
                Stage editStage = new Stage();
                editStage.setTitle("Edit Owner");

                // 创建标签和文本字段用于编辑属性
                Label firstNameLabel1 = new Label("First Name:");
                TextField editFirstNameField = new TextField(selectedOwner.getFirstName());

                Label lastNameLabel1 = new Label("Last Name:");
                TextField editLastNameField = new TextField(selectedOwner.getLastName());

                Label phoneLabel1 = new Label("Phone:");
                TextField editPhoneField = new TextField(selectedOwner.getPhone());

                Label emailLabel1 = new Label("Email:");
                TextField editEmailField = new TextField(selectedOwner.getEmail());

                Label addressLabel1 = new Label("Address:");
                TextField editAddressField = new TextField(selectedOwner.getAddress());

                // 创建保存按钮
                Button saveButton = new Button("Save");
                saveButton.setOnAction(event -> {
                    // 更新选定的 Owner 对象的属性值
                    selectedOwner.setFirstName(editFirstNameField.getText());
                    selectedOwner.setLastName(editLastNameField.getText());
                    selectedOwner.setPhone(editPhoneField.getText());
                    selectedOwner.setEmail(editEmailField.getText());
                    selectedOwner.setAddress(editAddressField.getText());

                    // 更新数据库中的 Owner 信息
                    updateOwner(selectedOwner);

                    // 关闭编辑窗口
                    editStage.close();

                    // 清空文本字段
                    clearFields();

                    // 刷新表格数据
                    refreshTable();
                });

                // 创建一个网格布局并将标签、文本字段和按钮添加到其中
                GridPane editGridPane = new GridPane();
                editGridPane.setHgap(10);
                editGridPane.setVgap(10);
                editGridPane.setPadding(new Insets(10));
                editGridPane.add(firstNameLabel, 0, 0);
                editGridPane.add(editFirstNameField, 1, 0);
                editGridPane.add(lastNameLabel, 0, 1);
                editGridPane.add(editLastNameField, 1, 1);
                editGridPane.add(phoneLabel, 0, 2);
                editGridPane.add(editPhoneField, 1, 2);
                editGridPane.add(emailLabel, 0, 3);
                editGridPane.add(editEmailField, 1, 3);
                editGridPane.add(addressLabel, 0, 4);
                editGridPane.add(editAddressField, 1, 4);
                editGridPane.add(saveButton, 1, 5);

                // 创建一个新的场景并将网格布局设置为根节点
                Scene editScene = new Scene(editGridPane, 400, 200);

                // 将场景设置到编辑窗口并显示窗口
                editStage.setScene(editScene);
                editStage.show();
            }
        });

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setRight(gridPane);
        root.setBottom(buttonBox);

        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Owners");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
    public static void main(String []args){launch(args);}
    static final String USER = "root";
    static final String PASS = "Mabohv123";
    static final String DB_URL = "jdbc:mysql://localhost:3306/realestatemanage?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private void add(String first_name,String last_name,String phone,String email,String address){
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // 准备SQL语句
            String sql = "INSERT INTO owner (firstname, lastname, phone, email, address) VALUES (?, ?, ?, ?, ?)";

            // 创建PreparedStatement对象
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // 设置参数值
                statement.setString(1, firstNameField.getText());
                statement.setString(2, lastNameField.getText());
                statement.setString(3, phoneField.getText());
                statement.setString(4, emailField.getText());
                statement.setString(5, addressField.getText());

                // 执行插入操作
                statement.executeUpdate();

                // 清空输入字段
                clearFields();

                // 刷新表格
                refreshTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void clearFields() {
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
    }
    private void refreshTable() {
        // 获取数据源，更新表格的数据
        ownerDao = new OwnerDao();
        List<Owner> owners = ownerDao.getAllOwners();
        table.getItems().clear();
        table.getItems().addAll(owners);
    }
    private void loadDataFromDatabase() {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS))  {
            // 创建和执行查询语句
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM owner");

            // 遍历结果集并添加到 OwnerDao 中
            while (resultSet.next()) {
                int  id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                Owner owner = new Owner(id, firstName, lastName, phone, email, address);
                //ownerDao.addOwner(owner);*/
                table.getItems().add(owner);
            }

            // 关闭结果集和语句
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*public void updateInDatebase(String newFirstName,String newLastName,String newPhone,String newEmail,String newAddress,Owner selectedOwner){
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS))  {
            // 创建和执行查询语句
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM owner");

            // 遍历结果集并添加到 OwnerDao 中
            String updateQuery = "UPDATE owners SET first_name = '" + newFirstName +
                    "', last_name = '" + newLastName +
                    "', phone = '" + newPhone +
                    "', email = '" + newEmail +
                    "', address = '" + newAddress +
                    "' WHERE id = " + selectedOwner.getId();
            statement.executeUpdate(updateQuery);

            // Close the statement and connection
            statement.close();
            connection.close();

            // Refresh the table to reflect the changes
            refreshTable();

            // Clear the text fields and disable editing
            clearFields();
            //disableEditing();
        } catch (SQLException e) {
            // Handle any exceptions that may occur during database operations
            e.printStackTrace();
        }
    }*/
    public void updateOwner(Owner owner) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER,PASS)) {
            String query = "UPDATE owner SET firstName=?, lastName=?, phone=?, email=?, address=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getLastName());
            statement.setString(3, owner.getPhone());
            statement.setString(4, owner.getEmail());
            statement.setString(5, owner.getAddress());
            statement.setInt(6, owner.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeOwner(Owner owner) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "DELETE FROM owner WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, owner.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}