Question
`https://www.educative.io/collection/page/5668639101419520/5649050225344512/5668600916475904/` -> `http://tinyurl.com/jlg8zpc`

1. Requirements
- MVP
  * URL -> short and unique link
  * a short link -> redirect to original link
- Stage 2
  * link will expire after default time
  * user can specify the expiration time

2. Estimations
- assume new urls shortening: 100 / s
- assume url redirection: 100 * 100 / s = 10k/s
- assume each url is 1KB (meta data, data)
- storage 5 years: 5 years * 12 months * 30 days * 24 hours * 3600 s * 100 / s * 1KB = 15 * 10^12 = 15 TB
- input bandwidth: 100 / s * 1KB = 100KB/s
- output bandwidth: 10KB/s * 1KB = 10MB/s
- memory: cache hot url requests 20% -> 10k/s * 3600s * 24hours  * 20% * 1KB ~= 170GB

3. System APIs
- String shortToLong(String api_key, String short)
- String longToShort(String api_key, String long)
- boolean deleteUrl(String api_key, String short)

4. Database
- tbl_user:
    user_id(PK)
    name
    email
    api_key
- tbl_record:
    short_url(PK)
    long_url
    user_id
SQL vs. NoSQL -> NoSQL, easier for horizontal scaling

5. System Design
* Client -> frontend server -> backend server API encoding -> database
* Client -> frontend server -> backend server API get cached long url or get from database



