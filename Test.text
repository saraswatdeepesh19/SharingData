Here’s a Copilot-optimized prompt designed to force senior-level backend code generation (not shallow boilerplate). This is structured so Copilot understands architecture, constraints, and expected coding standards.
✅ Copilot Prompt (Senior-Level Code Generation)
Use this as a top-level instruction in your project (README or comment block):
🚀 Prompt
Act as a senior backend engineer and generate a production-grade Spring Boot microservice using Java 21, Spring Boot, Spring Security (JWT), and Oracle DB.
Follow clean architecture, SOLID principles, and enterprise coding standards. Avoid basic/demo-level code.
1. Project Requirements
Build a secure service with:
Token Management System
Only one token request allowed globally within 15 minutes
First 2 requests → Manager approval
Subsequent requests → Lead approval
Token validity: 15 minutes
After expiry → reset request counter to 0
2. APIs to Implement
Public APIs
POST /token/request
Raise token request
Enforce global lock (15 min rule)
POST /token/approve
Approve request (Manager/Lead logic)
Generate JWT token
POST /token/reject
Secured APIs (JWT Required)
GET /db/table-status?tableName=
Check if table exists in Oracle
POST /db/query
Fetch specific columns with dynamic where clause
MUST prevent SQL injection
PUT /db/update
Update records with conditions
DELETE /db/delete
Delete records with conditions
3. Tech Constraints
Java 21
Spring Boot (latest)
Spring Security + JWT filter
Oracle DB
JPA/Hibernate
Maven project
4. Database Tables (Oracle)
Use prefix BS_DP_ for all tables:
BS_DP_USERS → authentication + roles
BS_DP_TOKEN_REQUEST → request tracking
BS_DP_TOKEN_AUDIT → issued tokens
BS_DP_OPERATION_LOG → all DB operations via token
5. Critical Business Logic
Implement thread-safe global request control:
Java
if (activeRequestExistsWithin15Min) {
    throw new BusinessException("Request already in progress");
}

if (requestCount < 2) {
    approver = MANAGER;
} else {
    approver = LEAD;
}
After token expiry:
Java
requestCount = 0;
6. Security Requirements
Implement JWT authentication filter
Include in token:
userId
role
expiry
Role-based authorization:
REQUESTOR → request token
MANAGER → approve first 2
LEAD → approve remaining
7. Code Expectations (VERY IMPORTANT)
Generate code with:
✅ Proper layered structure

controller/
service/
repository/
entity/
dto/
security/
exception/
util/
✅ Must include:
Global Exception Handler
Custom Exceptions (BusinessException, UnauthorizedException)
DTOs (no entity exposure)
Mapper layer (MapStruct or manual)
Logging (SLF4J)
Validation (Jakarta Validation)
Transaction management
8. Dynamic Query Handling (IMPORTANT)
DO NOT use raw string concatenation.
Use:
JPA Criteria API OR
Specification API OR
Parameterized queries
Ensure:
Column whitelist validation
Table name validation
Prevent SQL injection
9. Concurrency Handling
Use:
synchronized / ReentrantLock OR
DB-based locking OR
Redis (preferred for scalability)
10. Audit Requirements
Track:
Who requested token
Who approved
What APIs were called
Which table/operation executed
11. Deliverables
Generate:
Full project structure
Entity classes
Repository interfaces
Service layer with business logic
Controllers
Security config (JWT filter, config)
Utility classes (JWT utils)
Exception handling
Sample request/response payloads
Oracle-compatible schema (DDL)
12. Code Quality Expectations
Follow clean code practices
Avoid hardcoding
Use constants/configs
Use enums for roles/status
Write readable, maintainable code
Add meaningful comments only where needed
⚠️ Strict Instructions
Do NOT generate toy/demo code
Do NOT skip security or validation
Do NOT expose entities directly in APIs
Do NOT use unsafe SQL
🔥 Pro Tip
Paste this once, then ask Copilot:
“Generate step-by-step starting with project structure and security config”
If you want, next I can:
Generate actual production-ready code (Java 21 + Spring Boot 3)
Or review what Copilot generates and upgrade it to senior-level quality
Just say 👍