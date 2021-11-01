insert into tb_technology (catagory, tech_name, tech_id) values ('Cloud', 'AWS', '101');
insert into tb_technology (catagory, tech_name, tech_id) values ('Programming Language', 'Java', '102');
insert into tb_employee (email_id, emp_name, joining_date, mgr_id, project_code, role, emp_id) values ('cloudchain.ml@gmail.com', 'Sushil Prasad', sysdate, 'AM200501050700', '101001', 'Developer', 'SM201104180925');
insert into tb_technology_employees (technologies_tech_id, employees_emp_id) values (101,'SM201104180925');
insert into tb_technology_employees (technologies_tech_id, employees_emp_id) values (102,'SM201104180925');
commit;

/*
{
  
  "emailId": "cloudchain.ml@gmail.com",
  "empId": "SM201104180925",
  "empName": "Sushil Prasad",
  "joiningDate": "2020-10-02",
  "mgrId": "AM200501050700",
  "projectCode": 101001,
  "role": "Developer",
  "technologies": [
    {"techId": 101},
    {"techId": 102}
  ]
}
*/