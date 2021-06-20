/* result should include each person, regardless if there is an address for that person
https://www.diffen.com/difference/Inner_Join_vs_Outer_Join
*/

select FirstName, LastName, City, State from Person left join Address on Person.PersonId = Address.PersonId;
