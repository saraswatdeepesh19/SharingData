I have a Swagger/OpenAPI URL for this project.

Task:
Analyze the Swagger definition and generate a TABULAR documentation of all APIs.

For each API endpoint, extract and present the following fields in a table format:
1. HTTP Method (GET, POST, PUT, DELETE, etc.)
2. Endpoint URL (full path)
3. Request parameters
   - Path parameters
   - Query parameters
   - Request body (schema or fields)
4. Possible HTTP response codes (e.g., 200, 201, 400, 401, 403, 404, 500)
5. Response body structure for each response code (if available)
6. One-line functional description of what the API does (business purpose)

Output Requirements:
- Output must be in a clean, readable table (Markdown table preferred).
- One row per API endpoint.
- If multiple responses exist, list them clearly in the response column.
- Do NOT invent data—only use what is defined in Swagger.
- If something is missing in Swagger, explicitly mention “Not specified”.

Context:
This table will be used for:
- API documentation
- Functional understanding
- Review by developers and auditors

Start by parsing the Swagger URL and then generate the table.
