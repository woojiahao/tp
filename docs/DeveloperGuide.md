---
layout: page
title: Developer Guide
---

# UniCa$h Developer Guide [CS2103 T16-Group_3 TP]

**Name:** UniCa$h

**User Target Profile:** Our application is for university students who want to be more financially conscious about their spending habits, to enable them to make more economical decisions that provide students on a limited budget with more purchasing power.

**Value Proposition:** It provides university students with an intuitive and frictionless experience to have transparency on their expenditure to help them better understand their spendings so they can develop better financial habits as they transition into adulthood. 

## Potential Features:
- For every user > track individual finance (base)
- Input spending & set budgets (base)
- Tag another user and distribute the spendings during outings (extension)
- Viewing spending habits (extension)
- Provide price list comparison for all available food outlets near NUS (extension)
- Allow users to set repeating events (extension)
- Tagging people for payments gives them a notification (extension)

## Feature List [v1.2]
#### Track Finances
- Create expenses - Name, date, location, Tag (type/category)
- Delete expenses - Delete a single expense
- Edit expenses - Modify expenses
- Find expenses - Find an expense 
- Tabulate total expense  - Sum of all expenses & remaining balance

#### Track Income
- Input income - Add income: Name, date, amount
- Delete income
- Find income - find by name, date, value

#### Categorize Finances
- Add tags – provide category of spending
- Edit tags - change category of spending
- Remove tags - delete category of spending


## Use Cases
The following documents use cases for our application

For the following Use Cases (unless specified otherwise):
- The System is `UniCa$h`
- The Actor is `User`

#### Use Case: UC01 - Adding an income
**MSS:**
1. User enters the command to add an income with the correct format.
2. User submits the request.
3. UniCa$h adds the income to the income list and displays success message.
    
    Use Case ends

**Extensions**
- 2a. User enters an incorrect format
  - 2a1. UniCa$h displays an error message.
  - Use case resumes at step 1.
- 2b. User enters an extremely high number
  - 2b1. UniCa$h prompts the user with a warning of the input
  - 2b2. User confirms the request
  - Use case resumes at step 3.

#### Use Case: UC02 - Finding an income
**MSS:**
1. User enters the command to find an income with the correct format.
2. User submits the request.
3. UniCa$h filters all the incomes based on the specified filters and returns
the filtered income with a success message.

   Use Case ends

**Extensions**
- 2a. User enters an incorrect format.
  - 1a1. UniCa$h displays an error message.
  - Use case resumes at step 1.

- 3a. UniCa$h does not find any results matching the filter.
  - 3a1. UniCa$h displays a message saying no results found.
  - Use Case resumes at step 1

#### Use Case: UC03 - Delete an income
**MSS:**
1. User enters the command to delete an income with the correct format.
2. User submits the request.
3. UniCa$h finds the income based on the passed in arguments.
4. UniCa$h deletes the income.
5. UniCa$h displays success message.

   Use Case ends

**Extensions**
- 2a. User enters an incorrect format.
    - 1a1. UniCa$h displays an error message.
    - Use case resumes at step 1.

- 3a. UniCa$h does not find any income matching the provided arguments.
    - 3a1. UniCa$h displays a message saying no results found.
    - Use Case resumes at step 1


## Links
User Stories: [https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2](https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2)

Project Website: [https://ay2324s1-cs2103-t16-3.github.io/tp/](https://ay2324s1-cs2103-t16-3.github.io/tp/)
