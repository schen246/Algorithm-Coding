/* result should include each person, regardless if there is an address for that person*/

select FirstName, LastName, City, State from Person left join Address on Person.PersonId = Address.PersonId;
