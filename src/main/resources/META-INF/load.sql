-- Query for H2 that fails in MySQL because tue id is too long
INSERT INTO SeedUser (id, login, name, surname, password, userrole) values ('12345678123456781234567812345678', 'Systelab', 'Mark', 'Reinhold', 'wAfZzB+cBOH6JI1sY/+BRJ3mEVKFcRehrpxmiVTmmts=','ADMIN');
-- Query for MySQL that fails in H2 because User with login "Systelab" has been created in previous sentence
INSERT INTO SeedUser (id, login, name, surname, password, userrole) values ('1234567812345678', 'Systelab', 'Mark', 'Reinhold', 'wAfZzB+cBOH6JI1sY/+BRJ3mEVKFcRehrpxmiVTmmts=','ADMIN');

