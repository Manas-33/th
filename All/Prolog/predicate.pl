employee(ram, developer).
employee(shyam, tester).
employee(raj, manager).
employee(om, developer).

salary(ram, 60000).
salary(shyam, 20000).
salary(raj, 80000).
salary(om, 50000).

%Rules
underpaid(X):-salary(X,Salary),Salary<60000.
teamMember(X,Y):-employee(X,Z),employee(Y,Z),X\=Y.