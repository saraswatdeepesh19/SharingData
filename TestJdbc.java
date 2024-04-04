<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
</head>
<body>
<h1>User Management</h1>

<!-- Display User Count Data -->
<table border="1">
    <tr>
        <th>Month</th>
        <th>Year</th>
        <th>User Count</th>
    </tr>
    <tr th:each="user : ${users}">
        <td th:text="${user.month}"></td>
        <td th:text="${user.year}"></td>
        <td th:text="${user.userCount}"></td>
    </tr>
</table>

<!-- Form to Save User Count Data -->
<form action="#" th:action="@{/save}" method="post">
    <label for="month">Month:</label>
    <input type="number" id="month" name="month" required>
    <br>
    <label for="year">Year:</label>
    <input type="number" id="year" name="year" required>
    <br>
    <label for="userCount">User Count:</label>
    <input type="number" id="userCount" name="userCount" required>
    <br>
    <button type="submit">Save</button>
</form>

<!-- Link to Show Data Between Two Dates -->
<form action="#" th:action="@{/data}" method="get">
    <label for="startDate">Start Date:</label>
    <input type="date" id="startDate" name="startDate" required>
    <br>
    <label for="endDate">End Date:</label>
    <input type="date" id="endDate" name="endDate" required>
    <br>
    <button type="submit">Show Data</button>
</form>

</body>
</html>
