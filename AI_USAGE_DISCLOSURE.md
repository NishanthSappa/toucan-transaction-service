# AI Usage Disclosure

## Tool Used

ChatGPT was used as an AI coding and learning assistant during the development of this assignment.

## How It Was Used

AI assistance was used to:

- Understand the provided Spring Boot starter project.
- Understand the assignment requirements.
- Assist with designing the entity, repository, service, controller, and exception-handling structure.
- Assist with implementing REST APIs.
- Assist with validation and error handling.
- Assist with creating automated tests.
- Help troubleshoot compilation and implementation errors.

## Significant AI-Generated Suggestions

AI assistance suggested parts of the entity, repository, service, controller, exception handling, validation, and automated test implementation.

## What I Changed or Corrected

The suggestions were reviewed and implemented incrementally in the provided starter project.

Compilation errors encountered during development were identified and corrected before proceeding.

The status-transition behavior was deliberately implemented so that a transaction can move from `PENDING` to `SUCCESS` or `FAILED`, while transactions in a final state cannot be changed.

## How I Verified the Result

The application was built and tested locally using the Maven wrapper.

The automated tests cover:

1. Successful transaction creation
2. Validation failure
3. Duplicate Transaction ID rejection
4. Nonexistent transaction handling

The final test run completed successfully with `BUILD SUCCESS`.

The REST APIs were also run locally and manually tested.