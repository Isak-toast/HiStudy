# JOIN에 대해 설명해주세요.

> 작성자 : [정혜연](https://github.com/jeonglever)

<details>
<summary>Table of Contents</summary>

- [JOIN 이란](#JOIN-이란)
- [JOIN 종류](#JOIN-종류)
- [예제](#예제)
- [JOIN 시 유의할 점](#JOIN-시-유의할-점)
  </details>

---

## JOIN 이란

**JOIN**은 *두 개 이상의 테이블을 연결하여 데이터를 검색하는 데 사용*되는 중요한 연산입니다. <br/>
이를 통해 관계형 데이터베이스에서 데이터의 관계를 표현하고, 여러 테이블에 흩어진 정보를 합쳐서 의미 있는 결과를 얻을 수 있습니다.<br/>
JOIN 연산의 핵심은 두 테이블 간에 공통적으로 존재하는 열(키)을 기준으로 데이터를 결합하는 것입니다.<br/>

## JOIN 종류

![join](https://velog.velcdn.com/images/mingsso/post/850a40c5-6c24-440a-bdaf-3f8b83fad43d/image.png)

1. **INNER JOIN**<br/>
   가장 일반적인 JOIN 유형으로, 두 테이블 간의 교집합만을 결과로 반환합니다.<br/>
   두 테이블에서 매칭되는 행이 존재할 때만 결과에 포함됩니다.<br/>

2. **LEFT JOIN (LEFT OUTER JOIN)**<br/>
   왼쪽 테이블의 모든 행과 오른쪽 테이블에서 매칭되는 행을 반환합니다.<br/>
   오른쪽 테이블에 매칭되는 행이 없는 경우, 해당 필드는 NULL로 표시됩니다.<br/>

3. **RIGHT JOIN (RIGHT OUTER JOIN)**<br/>
   LEFT JOIN의 반대로, 오른쪽 테이블의 모든 행과 왼쪽 테이블에서 매칭되는 행을 반환합니다.<br/>
   왼쪽 테이블에 매칭되는 행이 없는 경우, 해당 필드는 NULL로 표시됩니다.<br/>

4. **FULL JOIN (FULL OUTER JOIN)**<br/>
   두 테이블의 합집합을 결과로 반환합니다.<br/>
   한 쪽 테이블에만 매칭되는 행도 결과에 포함되며, 매칭되지 않는 필드는 NULL로 표시됩니다.<br/>

5. **CROSS JOIN**<br/>
   두 테이블 간의 모든 가능한 조합을 반환합니다.<br/>
   조건 없이 두 테이블의 모든 행이 서로 조합되어, 두 테이블의 행 수를 곱한 만큼의 결과가 생성됩니다.<br/>

6. **SELF JOIN**<br/>
   테이블을 자기 자신과 조인하는 것입니다.<br/>
   복잡한 연산이나 테이블 내에서의 관계를 표현하기 위해 사용됩니다.<br/>

**JOIN 사용 시 고려 사항**<br/>
JOIN 조건은 보통 **ON 절**을 사용하여 지정합니다. 예를 들어, ON table1.column = table2.column과 같이 사용됩니다.<br/>
여러 개의 JOIN을 사용하여 다양한 테이블을 결합할 수 있습니다. 이 경우, 조인 순서와 조건이 중요합니다.<br/>
성능 최적화를 위해, 필요한 데이터만 선택적으로 조인하고, 인덱스를 적절히 사용하는 것이 중요합니다.<br/>
JOIN은 데이터베이스에서 강력한 도구로, 복잡한 데이터 관계를 해석하고, 다양한 데이터 분석과 리포트 작성에 필수적인 기능입니다.<br/>

**ON 절**<br/>
SQL 쿼리에서 ON 절은 JOIN 연산을 사용할 때 두 테이블 사이의 연결 조건을 지정하는 데 사용됩니다. <br/>
이 조건은 두 테이블이 어떻게 연결되어야 하는지, 즉 어떤 키(열)가 일치해야 하는지를 정의합니다. <br/>
ON 절은 특히 INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL JOIN과 같은 다양한 종류의 JOIN 연산에서 필수적입니다.<br/>

```sql
--ON 절 사용 예시
SELECT column_names
FROM table1
JOIN table2
ON table1.common_column = table2.common_column;
```

table1과 table2는 JOIN 연산을 수행할 두 개의 테이블입니다.<br/>
common_column은 두 테이블이 공유하는 열(키)로, 이 열의 값이 일치하는 레코드를 서로 연결합니다.<br/>
ON절은 두 테이블 간의 관계를 정의하여 어떤 기준으로 데이터를 결합할지 결정합니다. 이는 주로 두 테이블의 공통 필드(예: 외래 키)에 기반하여 수행됩니다.<br/>
또한 ON 절을 사용하여 정확한 결합 조건을 지정함으로써, 관련 없는 레코드가 결합되는 것을 방지하고, 쿼리의 결과가 정확하고 의미 있는 데이터를 반영하도록 합니다.<br/>

## 예제

Employees 테이블과 Department 테이블을 예시로 들어보겠습니다. <br/>

**Employees 테이블**
|EmployeeID|Name|DepartmentID|
|------|---|------|
|1|John Doe|1|
|2|Jane Doe|2|
|3|Max Smith|1|

**Department 테이블**
|DepartmentID|DepartmentName|
|------|---|
|1|HR|
|2|IT|
|3|Finance|

**1. INNER JOIN**
INNER JOIN은 두 테이블 모두에서 일치하는 항목이 있는 경우에만 결과를 반환합니다.

```sql
SELECT Employees.Name, Departments.DepartmentName
FROM Employees
INNER JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;
```

**결과:**
|Name|DepartmentName|
|------|---|
|John Doe|HR|
|Jane Doe|IT|
|Max Smith|HR|

**2. LEFT JOIN**<br/>
LEFT JOIN (또는 LEFT OUTER JOIN)은 왼쪽 테이블의 모든 레코드와 오른쪽 테이블에서 일치하는 레코드를 반환합니다.<br/>
일치하는 레코드가 없는 경우, 오른쪽 테이블의 결과는 NULL로 채워집니다.<br/>

예를 들어, "Employees" 테이블에 있는 모든 직원과 그들이 속한 부서의 이름을 가져오되, 어떤 직원이 속한 부서가 "Departments" 테이블에 없는 경우에도 그 직원의 정보를 포함시키고 싶다면 LEFT JOIN을 사용합니다.<br/>

```sql
-- 가정: Employees 테이블에 DepartmentID가 4인 직원이 추가되었다고 가정하기
INSERT INTO Employees (EmployeeID, Name) VALUES (4, New Employee)
```

이 Employees 테이블의 직원에게는 아직 부여된 부서가 없습니다. 그러나 LEFT JOIN을 사용하면 부서가 없어도 결과값에 노출됩니다.

```sql
SELECT Employees.Name, Departments.DepartmentName
FROM Employees
LEFT JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;
```

**결과:**
|Name|DepartmentName|
|------|---|
|John Doe|HR|
|Jane Doe|IT|
|Max Smith|HR|
|New Employee|NULL|

**3. RIGHT JOIN**<br/>
오른쪽 테이블(Departments)의 모든 행과 왼쪽 테이블(Employees)에서 매칭되는 행을 반환합니다. 매칭되지 않는 경우 NULL로 표시됩니다.<br/>

```sql
SELECT Employees.Name, Departments.DepartmentName
FROM Departments
RIGHT JOIN Employees ON Departments.DepartmentID = Employees.DepartmentID;
```

**결과:**

| DepartmentID | DepartmentName | EmployeeID | Name     |
| ------------ | -------------- | ---------- | -------- |
| 1            | HR             | 1          | John Doe |
| 2            | IT             | 2          | Jane Doe |
| 3            | Finance        | NULL       | NULL     |

**4. FULL JOIN**<br/>
두 테이블의 합집합을 반환합니다. 한 쪽 테이블에만 매칭되는 행도 결과에 포함되며, 매칭되지 않는 필드는 NULL로 표시됩니다.<br/>

```sql
SELECT Employees.Name, Departments.DepartmentName
FROM Employees
FULL JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;
```

**결과:**

| EmployeeID | Name         | DepartmentID | DepartmentName |
| ---------- | ------------ | ------------ | -------------- |
| 1          | John Doe     | 1            | HR             |
| 2          | Jane Doe     | 2            | IT             |
| 3          | Max Smith    | 1            | HR             |
| 4          | New Employee | NULL         | NULL           |
| NULL       | NULL         | 3            | Finance        |

**5. CROSS JOIN**<br/>
두 테이블 간의 모든 가능한 조합을 반환합니다. 조건 없이 두 테이블의 모든 행이 서로 조합됩니다.<br/>

```sql
SELECT Employees.Name, Departments.DepartmentName
FROM Employees
CROSS JOIN Departments;

```

**결과:**
|EmployeeID|Name|DepartmentID|DepartmentName|
|----------|-----|----|--------------|
|1|John Doe|1|HR|
|1|John Doe|2|IT|
|1|John Doe|3|Finance|
|2|Jane Doe|1|HR|
|2|Jane Doe|2|IT|
|2|Jane Doe|3|Finance|
|3|Max Smith|1|HR|
|3|Max Smith|2|IT|
|3|Max Smith|3|Finance|
|4|New Employee|1|HR|
|4|New Employee|2|IT|
|4|New Employee|3|Finance|

**6. SELF JOIN**<br/>
테이블을 자기 자신과 조인합니다. 이 예시에서는 Employees 테이블 내에서 각 직원과 동일한 부서에 속한 다른 직원을 찾습니다<br/>

```sql
SELECT A.Name AS EmployeeName, B.Name AS ColleagueName
FROM Employees A, Employees B
WHERE A.DepartmentID = B.DepartmentID AND A.EmployeeID != B.EmployeeID;
```

**결과:**
제공된 조건에서 동일 부서에 속한 다른 직원을 찾는 쿼리의 결과는 없기 때문에 도출되는 결과는 비어있습니다.<br/>

## JOIN 시 유의할 점

**1. 조인으로 인한 성능 저하**
큰 테이블을 조인할 때 성능이 저하될 수 있습니다. 이를 해결하기 위해 쿼리 최적화, 적절한 인덱싱, 데이터 분할 등의 기술을 적용할 수 있습니다.<br/>
**2. 조인으로 인한 중복 행**
조인 조건이 너무 넓거나 잘못 지정되었을 때, 예상치 못한 중복 행이 결과에 포함될 수 있습니다. <br/>
이를 방지하기 위해 쿼리를 정확히 작성하고, 필요한 경우 DISTINCT 키워드를 사용하여 중복을 제거합니다.
