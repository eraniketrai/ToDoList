<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View ToDo Item List</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">

    <!-- jQuery and Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <style>
        a {
            color: white;
        }
        a:hover {
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="p-3">ToDo Item List</h1>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Mark Completed</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="todo" items="${list}">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.title}</td>
                        <td>${todo.date}</td>
                        <td>${todo.status}</td>
                        <td>
                            <a href="/updateToDoStatus/${todo.id}" class="btn btn-success">Mark Complete</a>
                        </td>
                        <td>
                            <a href="/editToDoItem/${todo.id}" class="btn btn-primary">Edit</a>
                        </td>
                        <td>
                            <a href="/deleteToDoItem/${todo.id}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="/addToDoItem" class="btn btn-primary btn-block">Add New ToDo Item</a>
    </div>

    <script>
        window.onload = function() {
            var msg = "${message}" || "";

            if (msg === "Save Success") {
                toastr.success("Item added successfully!!");
            } else if (msg === "Delete Success") {
                toastr.success("Item deleted successfully!!");
            } else if (msg === "Delete Failure") {
                toastr.error("Some error occurred, couldn't delete item");
            } else if (msg === "Edit Success") {
                toastr.success("Item updated successfully!!");
            }

            toastr.options = {
                closeButton: true,
                progressBar: true,
                positionClass: "toast-top-right",
                timeOut: "5000"
            };
        };
    </script>
</body>
</html>
