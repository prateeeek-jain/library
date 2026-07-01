1. Could have thought of making penalty amount and number of days a member can borrow books as configurable in the membership type enum.
2. Member cannot be a record as it is mutable as any member can become a premium member and vice versa.
3. I should have thought of index on Id for each table. This will give db like structure.
4. All the Id fields should be final.
5. Never return a list that you saved in memory. Return a copy of the list. So that others cannot modify the list.
6. Use optional if a method can return null values. Then it will be explicit for the caller to handle the null case.
7. There should be single source of truth. Earlier I was saving loans and members holding onto books in two separate tables.