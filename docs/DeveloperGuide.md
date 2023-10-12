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

#### Use Case: UC05 - Listing All Transactions
**MSS:**
1. User enters the command to list all expenses with the correct format (i.e. no parameters).
2. User submits the request.
3. UniCa$h retrieves the list of all expenses and displays them for the User.

   Use Case ends.

**Extensions**
- 2a. User enters the command with the incorrect format (i.e. with parameters).
    - 2a1. UniCa$h displays an error message, requests for the correct format.
    - Use case resumes from step 1.

- 3a. There are no expenses for UniCa$h to retrieve.
    - 3a1. UniCa$h displays a message informing the User that there are no expenses. 
    - Use Case ends.

#### Use Case: UC06 - Finding Transaction
**MSS:**
1. User enters the command to find transaction with the correct format.
2. User submits the request.
3. UniCa$h filters the expenses based on the specified filters and returns the filtered list of expenses with a success message.

   Use Case ends.

**Extensions**
- 2a. User enters an incorrect format.
    - 2a1. UniCa$h displays an error message, requests for correct format.
    - 2a2. User enters command with new format.
  
    Steps 2a1-2a2 are repeated until the format entered is correct.

    Use case resumes from Step 3.

- 3a. UniCa$h does not find any results matching the filter.
    - 3a1. UniCa$h displays a message informing the user that no results were found.
    - Use Case ends.

#### Use Case: UC07 - Tabulate Total Expenses
**MSS:**
1. User enters the command to tabulate total expenses.
2. User submits the request.
3. UniCa$h tabulates the expenditure based on the parameters passed in.
4. UniCa$h displays the tabulated expenditure.

   Use Case ends.

**Extensions**
- 2a. User detects an issue with the command entered.
    - 2a1. UniCa$h displays an error message, requests for command to be re-entered.
    - 2a2. User enters command again.

  Steps 2a1-2a2 are repeated until the command entered is correct.

  Use case resumes from Step 3.

## Links
User Stories: [https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2](https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2)

Project Website: [https://ay2324s1-cs2103-t16-3.github.io/tp/](https://ay2324s1-cs2103-t16-3.github.io/tp/)
