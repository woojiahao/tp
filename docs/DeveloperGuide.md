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

#### Use Case: UC08 - Editing an expense
**MSS:**
1. User enters the command to edit details about an expense with the correct format
2. User submits the request
3. UniCa$h makes the edits specified in the command and displays the successful edits made to the expense

   Use Case ends.

**Extensions**
- 2a. User enters an incorrect format. (e.g. amount is not expressed as a float, date is not in YYYY-mm-dd format)
    - 2a1. UniCa$h displays an error message, requests for correct format.
    - Use Case ends.

- 2b. User requests to edit an expense that does not exist
    - 2b1. UniCa$h displays an error message informing the user that the expense does not exist
    - Use Case ends.

- 2c. User attempts to reset the "name" and/or "amount" attribute to default
    - 2c1. UniCa$h displays an error message informing the user that the attribute cannot be reset to default
    - Use Case ends.

- 2d. User does not specify which attribute to edit
    - 2d1. UniCa$h displays an error message and displays the correct way to format the edit command.
    - Use Case ends.

#### Use Case: UC09 - Resetting expense attributes to default
**MSS:**
1. User enters the command to reset attributes of an expense with the correct format
2. User submits the request
3. UniCa$h makes the edits specified in the command and displays the successful edits made to the expense

   Use Case ends.

**Extensions**
- 2a. User attempts to reset the "name" and/or "amount" attribute to default
    - 2a1. UniCa$h displays an error message informing the user that the attribute cannot be reset to default
    - Use Case ends.

## Links
User Stories: [https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2](https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2)

Project Website: [https://ay2324s1-cs2103-t16-3.github.io/tp/](https://ay2324s1-cs2103-t16-3.github.io/tp/)
