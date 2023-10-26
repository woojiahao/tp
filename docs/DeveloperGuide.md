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

There are 4 main groups of features that we have designed and either implemented
or propose to implement in the future.

These are:
1. Transaction Management Features
2. Budget Management and Monitoring Features
3. General Utility Features
4. User Interface Features

### Feature Group 1 - Transactions Management

#### The Transaction Class

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

##### Details

1. The user specifies the transaction to be added by stating the name, amount, transaction type as well as any other optional fields.
2. The input will be parsed by `AddCommandTransactionParser`, and if it is invalid, `ParserException` is thrown, prompting for the user to enter again.
3. If the input is valid, a `Transaction` object is created and passed into the `AddTransactionCommand` to be executed by the `LogicManager`.
4. The `LogicManager` will then invoke the execute command, adding the `Transaction` to the UniCash.

Note that only the `Category` field is allowed to be specified multiple times, while the other fields can only be specified once, else
a `ParserException` is thrown. Another noteworthy point is that `Category` that are added are to be case-insensitively unique and can only be up to 
a specified value in the `UniqueCategoryList` class. Else, a `ParserException` would be thrown.

#### Delete Transaction

##### Overview

The `DeleteCommand` function deletes an existing `Transaction` from `TransactionList` in UniCash.

The activity diagram of deleting a Transaction is as shown below

<img src="images/unicash/DeleteTransactionActivityDiagram.png" width="1200" />

The following sequence diagram shows the interaction between different components of UniCash.

<img src="images/unicash/DeleteTransactionSequenceDiagram.png" width="1200" />

The above sequence diagram omits details on the filtering of `TransactionList` and assumes that
the displayed `TransactionList` is showing all transactions. However, the logic of the `DeleteCommand`
remains the same for all list deletion.

ℹ️ **Note:** The lifeline for `DeleteTransactionCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML,
the lifeline reaches the end of diagram.

##### Details

1. The user specifies the transaction to be deleted by stating the integer index of the transaction to be deleted.
2. The input will be parsed by `DeleteTransactionCommandParser` and if the provided input is invalid, `ParseException` will be thrown,
and the user is prompted to enter the command again with the correct input.
3. If the input is valid, an `Index` object is created with the given input integer, and passed into `DeleteTransactionCommand` to be executed
by `LogicManager`
4. `LogicManger` will invoke the `execute` method of `DeleteTransactionCommand` which will delete the `Transaction` from UniCash.

It is important to take note that when the user input is parsed, it is based on the currently displayed `TransactionList` inside
`TransactionListPanel`. This means that even if a `TransactionList` contains `10` transactions, given a specific nominally valid
number like `7`, it can still throw a `ParseException` if the shown `TransactionList` contains less than `7` items. This feature is
intentional, as the User is able to, for example, `find` a particular group of transaction and immediately delete those transactions
by just looking at their displayed index number without having to refer to an external identifier of that transaction. UniCash will
automatically handle the visual ordering and representation of transactions with the `TransactionsListPanel` in the UI. The details
and diagrams for this part will be elaborated further in the UI section (and other relevant sections) of this Developer Guide.


### Feature Group 2 - Budget Management and Monitoring

### Feature Group 3 - General Utility Features

This includes commands such as Clear, Reset, Help and Exit.

#### Clear Transactions

##### Overview

The `ClearTransactionsCommand` deletes all existing `Transactions` from `TransactionList` in UniCash.

The activity diagram of clearing all transactions is as shown below

<img src="images/unicash/ClearTransactionsActivityDiagram.png" width="1200" />

The following sequence diagram shows the interaction between different components of UniCash.

<img src="images/unicash/ClearTransactionsSequenceDiagram.png" width="1200" />

**Note:** Given that `ClearTransactionsCommand` takes in no arguments, it does not have an associated Parser class
like the other `Command` classes. This is currently the case, however, given that the command entirely erases the
existing Unicash, a `ClearTransactionsCommandParser` is proposed to be implemented at a later date to ensure an
additional layer of safety for the User.

##### Details

1. The user inputs the command to reset unicash
2. A `ClearTransactionsCommand` object is created with no arguments.
3. `LogicManager` will invoke the `execute` method of `ClearTransactionsCommand` 
which will replace the existing `Model` property with a new `UniCash` object which 
would contain an empty `TransactionList`.

Here, it must be noted that unlike `DeleteTransactionCommand`, individual transactions in the `TransactionList`
are not deleted singularly. As opposed to iteratively deleting each transaction in the `TransactionList`, the more
efficient way to achieve the same effect would be to simply set the `Model` contained in `LogicManager` to an new 
`UniCash` object, as the newly created `UniCash` object would now have an empty `TransactionList` encapsulated within.
This emulates the iterative deletion of all transactions in the `TransactionList`.


### Feature Group 4 - User Interface Features

## Links
User Stories: [https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2](https://github.com/orgs/AY2324S1-CS2103-T16-3/projects/1/views/2)

Project Website: [https://ay2324s1-cs2103-t16-3.github.io/tp/](https://ay2324s1-cs2103-t16-3.github.io/tp/)
