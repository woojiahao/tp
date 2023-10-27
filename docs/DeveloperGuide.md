---
layout: page
title: Developer Guide
---

# UniCa$h Developer Guide [CS2103 T16-Group_3 TP]

**Name:** UniCa$h

**User Target Profile:** Our application is for university students who want to be more financially conscious about their spending habits, to enable them to make more economical decisions that provide students on a limited budget with more purchasing power.

**Value Proposition:** It provides university students with an intuitive and frictionless experience to have transparency on their expenditure to help them better understand their spendings so they can develop better financial habits as they transition into adulthood. 

## Potential Features:
- For every user > track individual finances (base)
- Input spending & set budgets (base)
- Tag another user and distribute the spendings during outings (extension)
- Viewing spending habits (extension)
- Provide price list comparison for all available food outlets near NUS (extension)
- Allow users to set repeating events (extension)
- Tagging people for payments gives them a notification (extension)

## Feature List [v1.2]
#### Track Finances
- Create transactions - Name, date, location, Tag (type/category)
  - Input either Income or Expense
- Delete transactions - Delete a single transaction
  - Remove either an Income or an Expense
- Clear transactions - Mass delete all transactions
- Edit transactions - Modify transactions
- Find transaction - Find a transaction by a given keyword 
- Tabulate total expenditure  - Sum of all expenses & remaining balance

#### Categorize Finances
- Add tags – provide category of transaction
- Edit tags - change category of transaction
- Remove tags - delete category of transaction


## Use Cases
The following documents use cases for our application

For the following Use Cases (unless specified otherwise):
- The System is `UniCa$h`
- The Actor is `User`

#### Use Case: UC01 - Adding a transaction
**MSS:**
1. User enters the command to add a transaction with the correct format.
2. User submits the request.
3. UniCa$h adds the transaction to the transactions list and displays success message.
    
    Use Case ends

**Extensions**
- 2a. User enters an incorrect format
  - 2a1. UniCa$h displays an error message with the correct command format.
  - Use case resumes at step 1.
- 2b. User enters an extremely high number
  - 2b1. UniCa$h prompts the user with a warning of the input
  - 2b2. User confirms the request
  - Use case resumes at step 3.

#### Use Case: UC02 - Finding a transaction
**MSS:**
1. User enters the command to find a transaction with the correct format.
2. User submits the request.
3. UniCa$h filters all the transactions based on the specified filters and returns
the filtered transaction with a success message.

   Use Case ends

**Extensions**
- 2a. User enters an incorrect format.
  - 1a1. UniCa$h displays an error message with the correct command format.
  - Use case resumes at step 1.

- 3a. UniCa$h does not find any results matching the filter.
  - 3a1. UniCa$h displays a message saying no results found.
  - Use Case resumes at step 1

#### Use Case: UC03 - Delete a transaction
**MSS:**
1. User enters the command to delete a transaction with the correct format.
2. User submits the request.
3. UniCa$h finds the transaction based on the passed in arguments.
4. UniCa$h deletes the transaction.
5. UniCa$h displays success message.

   Use Case ends

**Extensions**
- 2a. User enters an incorrect format.
    - 2a1. UniCa$h displays an error message with the correct command format.
    - Use case resumes at step 1.

- 3a. UniCa$h does not find any transactions matching the provided arguments.
    - 3a1. UniCa$h displays a message saying no results found.
    - Use Case resumes at step 1


#### Use Case: UC05 - Listing All Transactions
**MSS:**
1. User enters the command to list all transactions with the correct format (i.e. no parameters).
2. User submits the request.
3. UniCa$h retrieves the list of all transactions and displays them for the User.

   Use Case ends.

**Extensions**
- 2a. User enters the command with the incorrect format (i.e. with parameters).
    - 2a1. UniCa$h displays an error message, requests for the correct format.
    - Use case resumes from step 1.

- 3a. There are no transactions for UniCa$h to retrieve.
    - 3a1. UniCa$h displays a message informing the User that there are no expenses. 
    - Use Case ends.

#### Use Case: UC06 - Finding a Transaction
**MSS:**
1. User enters the command to find transaction with the correct format.
2. User submits the request.
3. UniCa$h filters the transactions based on the specified filters and returns the filtered list of expenses with a success message.

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

#### Use Case: UC07 - Tabulate Total Expenditure
**MSS:**
1. User enters the command to tabulate total expenditure.
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

#### Use Case: UC08 - Clear all transactions
**MSS:**
1. User enters the command to clear all transactions with the correct format. (i.e. no parameters)
2. User submits the request.
3. UniCa$h deletes all transactions in the transactions list.
4. UniCa$h displays success message.

   Use Case ends

**Extensions**
- 2a. User enters an incorrect format.
  - 2a1. UniCa$h displays an error message with the correct command format.
  - Use case resumes at step 1.

- 3a. UniCa$h finds an empty transactions list.
  - 3a1. UniCa$h displays a message saying that transactions list is empty.
  - Use Case resumes at step 1

#### Use Case: UC09 - Show UniCa$h Help
**MSS:**
1. User enters the command to show help with the correct format. (i.e. no parameters)
2. User submits the request.
3. UniCa$h displays help message in the help window.

   Use Case ends

**Extensions**
- 2a. User enters an incorrect format.
  - 2a1. UniCa$h displays an error message with the correct command format.
  - Use case resumes at step 1.


#### Use Case: UC10 - Exit UniCa$h
**MSS:**
1. User enters the command to exit UniCa$h with the correct format (i.e. no parameters)
2. User submits the request. 
3. UniCa$h displays exit message and application closes

   Use Case ends

**Extensions**
- 2a. User enters an incorrect format.
  - 2a1. UniCa$h displays an error message with the correct command format.
  - Use case resumes at step 1.

## Implementation

This section aims to describe the implementation of the features in UniCa$h.

There are 3 main group of features we have come up with.
1. Transaction Management
2. Budget Management and Monitoring
3. General Utility Features

### Transaction

<img src="images/unicash/TransactionClassDiagram.png" width="700" />

UniCa$h tracks transactions with the use of `TransactionList` and `Transaction`. `TransactionList` acts
as a wrapper for a list of `Transaction` that enforces no `null`.

The `Transaction` class is composed of the following fields

1. `Name`: The name of the transaction.
2. `Type`: The transaction type of the transaction. UniCash supports expense and income only.
3. `Amount`: The monetary value of the transaction.
4. `DateTime`: The date and time of the transaction to be recorded.
5. `Location`: The location where the transaction took place.
6. `UniqueCategoryList`: A list of categories to be tagged with the transaction.

For the attributes, there are 3 compulsory fields, namely `Name`, `Type` and
`Amount`. The remaining fields would fall back to a default value if not specified.

#### Add Transaction

##### Overview

The `add_transaction` command adds a new `Transaction` to the `TransactionList` in UniCash.

The activity diagram of adding a Transaction is as shown below

<img src="images/unicash/AddTransactionActivityDiagram.png" width="1200" />

The following sequence diagram shows how the different components of UniCash interact with each other

<img src="images/unicash/AddTransactionSequenceDiagram.png" width="1200" />

The above sequence diagram omits details on the creation of the attributes of a `Transaction` such as 
`Name`, `Type` and `Amount` as it would make the diagram cluttered and difficult to read without adding
additional value.

ℹ️ **Note:** The lifeline for `AddTransactionCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML,
the lifeline reaches the end of diagram.


#### Edit Transaction

##### Overview

The `edit_transaction` command edits an existing `Transaction` from the `TransactionList` in UniCash.

The following sequence diagram shows how the different components of UniCash interact with each other. It is further explained in the **Details** section below.

<img src="images/unicash/EditTransactionSequenceDiagram.png" width="1200" />


##### Details

1. The user specifies the transaction to be edited by first stating the (one-based) index of the transaction they want to edit. This
is followed by listing the fields and updated values that they would like to edit.
(E.g. `edit_transaction 1 n/Food Clique c/food`)
2. The raw user input will be parsed by `EditCommandTransactionParser`, and if it is invalid, `ParserException` is thrown, prompting for the user to enter again. It is important to note that at this point, a valid index is any integer that is 1 or greater.
3. If the input is valid, the `EditCommandTransactionParser` creates an `EditTransactionDescriptor` object, which contains the edits which the user wishes to make to the transaction.
4. An `EditTransactionCommand` is constructed with the one-based index of the transaction to edit and the `EditTransactionDescriptor` object. This `EditTransactionCommand` is then returned by the `EditCommandTransactionParser`
5. The `LogicManager` will then invoke the execute command of the `EditTransactionCommand`, editing the `Transaction` in UniCash.
6. The `EditTransactionCommand#execute` method then first checks if the one-based index is too large (i.e. if there are only `n` transactions, but the index provided is greater than `n`). If this is true, then a `CommandException` is thrown,
prompting the user to input an index that is at most, the number of transactions available. If the `CommandException` is not thrown at the step above, then the execution proceeds.
7. The `EditTransactionCommand#execute` method then calls the `createEditedTransaction` method, which creates a new `Transaction` object with the updated values after editing. Note that this `Transaction` object is a different object from what is stored in UniCash.
8. The `EditTransactionCommand#execute` method then updates UniCash with the new `Transaction` using the `Model#setTransaction` method.

Note that although all fields can be edited, the `Name`, `Amount`, and `Type` fields cannot be left blank. The constraints laid
out in the **Add Transaction** section above also remain.

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
