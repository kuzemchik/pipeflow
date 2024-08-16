# PipeFlow
Opinionated data processing framework.


## Core idea
Introduce restrictions and code organizations for data pipelines along with common functionality.

## Maturity
Different iterations of this approach were used in production to process petabytes of data.
The framework is designed to be focused on readability and at the same time to be flexible and maintainable.

## TODO:
- [x] Base pipe class
  - [x] Lazy evaluation
  - [x] Foreach and Flatmap support (different for comprehensions use cases)
  - [x] Base tests
- [x] Apache Spark support for Java 13+
- [ ] DateStamp and common date related functions
- [ ] Integration with scheduler (Airflow?)
