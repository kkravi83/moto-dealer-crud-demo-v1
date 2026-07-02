## Plan: Add getDealerByCity

TL;DR - Add a repository query, a service method, and a controller endpoint to return dealers by city (case-insensitive). Keep behavior simple: return 200 with an empty list when no matches.

**Steps**
1. Repository: add `List<Dealer> findByCityIgnoreCase(String city)` to `DealerRepository` to perform a case-insensitive search.
2. Service: add `List<Dealer> getDealersByCity(String city)` to `DealerService` that calls the repository method and returns the list. (Optionally throw `ResourceNotFoundException` if empty — decision noted below.)
3. Controller: add a `GET` endpoint in `DealerController` such as `@GetMapping("/city/{city}")` with handler `getDealerByCity(@PathVariable String city)` that returns the list from the service.
4. Tests: add a unit test in `DealerControllerTest` (or a new test) that mocks the service to return a list and asserts the controller returns the JSON array; optionally add an integration test using `data.sql`.
5. Manual verification: run unit tests, start the app and curl the new endpoint.

**Relevant files**
- src/main/java/com/example/copilotcrud/repository/DealerRepository.java — add query method `findByCityIgnoreCase`.
- src/main/java/com/example/copilotcrud/service/DealerService.java — add `getDealersByCity` service method.
- src/main/java/com/example/copilotcrud/controller/DealerController.java — add controller endpoint `GET /api/dealers/city/{city}`.
- src/test/java/com/example/copilotcrud/controller/DealerControllerTest.java — add/modify tests to cover the new endpoint.

**Verification**
1. Run unit tests: `mvn test` and ensure new/updated tests pass.
2. Start the app: `mvn spring-boot:run` and then test with curl: `curl "http://localhost:8080/api/dealers/city/Tokyo"` — expect 200 and JSON array (possibly empty).
3. Optional integration: add test data to `src/main/resources/data.sql` and verify the endpoint returns expected rows.

**Decisions / Assumptions**
- Default behavior: return HTTP 200 with an empty JSON array when no dealers exist for the requested city. If you prefer 404 instead, the service should throw `ResourceNotFoundException` when the result list is empty.
- Use path parameter `/city/{city}` for a simple API. Alternative: support query parameter `GET /api/dealers?city=...` if you plan to add multiple filters.

**Further Considerations**
1. URL encoding & spaces: city names with spaces must be URL-encoded or use query parameter to avoid encoding issues.
2. Performance: for large datasets consider adding a DB index on `city` and/or paginating results (`Pageable`).
3. Normalization: consider trimming and normalizing input (e.g., collapse whitespace) if cities are entered inconsistently.

If you want, I can now apply the code changes and add a test. Let me know which behavior you prefer for "no matches" (200 empty array or 404).