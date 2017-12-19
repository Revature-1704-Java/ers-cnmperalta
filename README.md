# Employee Reimbursement System
<p>This provides a simple GUI to use the system.</p>
<p>Run ers.sql to get a database of dummy data. It drops the database if it exists and creates the tables, constraints, sequences, triggers, and stored procedures.</p>
<p>To create an account, the user must already have an entry in the Employee table and must use their email address to begin account creation. They must then give a password, which will be used to update the Password field in their row of the Employee table. The Password field is initially null (no value supplied when the mock database was populated). For now, passwords are stored as plaintext.</p>
<p>This project currently has minimal error checking/exception handling.</p>
<p>This project currently has JUnit testing, but only for getters and setters.</p>