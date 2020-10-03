insert into tb_department (dept_name, dept_id) values ('Cloud BL', 101);
insert into tb_department (dept_name, dept_id) values ('Narketing', 102);
insert into tb_project (budget, department_dept_id, description, project_name, project_status, start_date, project_code) values (10000, 101, 'Spring Boot Cloud Project', 'Spring Cloud Service', 'Analysis', sysdate, 101001);
commit;

/*
{
  "budget": 1000,
  "department": {
    "deptId": 101
  },
  "description": "Spring Cloud Project",
  "projectCode": 101001,
  "projectName": "Cloud Boot",
  "projectStatus": "Analysis",
  "startDate": "2020-10-02"
}
*/
