---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

---

### Edit Expense [coming soon]
Allows a user to make edits to an existing expense, and all associated information.

Command: `edit <expense_id> -<name of attribute 1> <new attribute 1 value> [-<name of attribute N> <new attribute N value> …]`

Command Argument: `expense_id` is the ID of the expense to edit.

Command Options:

| Option Name        | Optional? | Purpose                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
|--------------------|-----------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| -name of attribute | No        | The attribute to make edits to. Possible values: `name`, `amount`, `category`, `date`, `location`<br/><br/>Note: If `name of attribute` is date, then the associated `new attribute value` must be in format: `dd/MM/yyyy`.<br/>Note: If `name of attribute` is amount, then the associated `new attribute value` must be a number.<br/>Note: If `name of attribute` is not name or amount, then the associated `new attribute value` can be empty if the user wants to reset the attribute to the default value (NULL for location and Others for category). |
| ...                | Yes       | More `name of attribute` `new attribute value` pairs to make more edits to the same expense                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |

#### Expected Outputs

##### Successful Execution

###### Example 1

>**Case**: Editing one attribute of expense 3
>
>**Input**: `edit 3 -location online`
>
>**Output**:
> ```
> Successful edits to expense 3:
> location : online
> ```

###### Example 2

>**Case**: Setting the expense’s category to be default of “Others”
>
>**Input**: `edit 2 -category -location frontier pasta express -amount 5.8
`
>
>**Output**:
> ```
> Successful edits to expense 1:
> category : “Others”
> location : frontier pasta express
> amount : $5.80
> ```

#### Failed Execution

###### Example 1

>**Case**: No attributes to edit
>
>**Input**: `edit 1`
>
>**Output**:
> ```
> Please input an attribute to edit, and the new value to change the attribute to.
> Syntax: edit <integer> -<name of attribute> <new attribute value>
> ```

###### Example 2

>**Case**: New attribute value for `name` is empty
>
>**Input**: `edit 1 -name`
>
>**Output**:
> ```
> Attributes “name” and “amount” cannot be empty
> ```

###### Example 3

>**Case**: There are only 10 expenses in the list, but user tries to edit expense 100000
>
>**Input**: `edit 100000 -location online`
>
>**Output**:
> ```
> There are only 10 expenses. Please provide an integer between 1 and 10 (received: 100000)
> ```

###### Example 4

>**Case**: Wrong input format for “date” and “amount” attribute
>
>**Input**: `edit 2 -date yesterday -amount 5.80.`
>
>**Output**:
> ```
> Attribute “date” must be of the form dd/MM/yyyy (received: yesterday)
> Attribute “amount” must be a number (received: 5.80.)
> ```

### Create Income
Allows a user to register an inflow of money (income) into the application.
Our application will store an income based on the name, value, date.

Command: `create_income <name> [-value <value of income>] [-date <date of expense>]`

Command Argument: `name` represents the name of the income to be added.

Command Options:

| Option Name | Optional? | Purpose                                                                                                                |
|-------------|-----------|------------------------------------------------------------------------------------------------------------------------|
| -value      | No        | Value of expense. Represents a positive number (integer/ float).                                                       |
| -date       | Yes       | Date of when the expense was made. Follows format  dd/MM/yyyy <br><br>  Defaults to date of creation if not specified. |

#### Expected Outputs

##### Successful Execution

###### Example 1

>**Case**:  Create “work at lifo” income dated 19/09/2023 with value of 900.
>
>**Input**: `create_income work at liho -date 19/09/2023 -value 900`
>
>**Output**: Successfully created income “work at liho”!
>
>**Remark**: The income will be dated 19/09/2023.

##### Failed Execution

###### Example 1
>**Case**: Missing `name` of income
>
>**Input**: `create_income`
>
>**Output**: Cannot create income without income name. 
> Please specify the income name as such: `create_income <name> -value <value>`

###### Example 2
>**Case**: Missing `value` of income
>
>**Input**: `create_income working`
>
>**Output**: Cannot create income without income value.
> Please specify the income name as such: `create_income <name> -value <value>`

###### Example 3
>**Case**: Invalid `value` form (not positive number)
>
>**Input**: `create_income working -value hi`
>
>**Output**: Cannot create income due to invalid income value type. Ensure that it is a positive number.

###### Example 4
>**Case**: Invalid `date` of income
>
>**Input**: `create_income working -value 1300 -date today`
>
>**Output**: Cannot create income due to invalid date format. Ensure that it follows dd/MM/yyyy.


### Delete Income
Allows a user to delete an income previously added into the application.

Command: `delete_income <name>`

#### Expected Outputs

##### Successful Execution

###### Example 1
>**Case**: Delete “work at liho” income.
>
>**Input**: `delete_income work at liho`
>
>**Output**: Successfully deleted expense “work at liho”

##### Failed Execution

###### Example 1
>**Case**: Missing `name` of income
>
>**Input**: `delete_income`
>
>**Output**: Cannot delete income without income `name`. 
> Please specify the income name as such: `delete_income <name>`

### Find Income
Allows a user to search for an income(s) that was previously entered.
User can find income(s) through name.

Command: `find_income [-name <name of income>] [-value_more <value of income>] [-value_less <value of income>] [-date <date of income>]`

Command Options:

| Option Name | Optional? | Purpose                                                                                                |
|-------------|-----------|--------------------------------------------------------------------------------------------------------|
| -name       | Yes       | Name of income to find.                                                                                |
| -value_more | Yes       | Value of income, used to filter income more than value.                                                |
| -value_less | Yes       | Value of income, used to filter income less than value.                                                |
| -date       | Yes       | Date of when the income was made. Follows format dd/MM/yyyy.<br><br>Filters income added on that date. |

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
If no options are specified, all income is returned.
</div>

#### Expected Outputs

##### Successful Execution

###### Example 1
>**Case**: Find “work at liho” income.
>
>**Input**: `find_income work at liho`
>
>**Output**: Successfully found income “work at liho”. Display information related to the income

##### Failed Execution

###### Example 1
>**Case**: Missing `name` of income
>
>**Input**: `find_income`
>
>**Output**: Cannot find income without income name.
> Please specify the income name as such: `find_income <name>`

###### Example 2
>**Case**: Invalid `date` format
>
>**Input**: `find_income work at liho -date tomorrow`
>
>**Output**: Cannot find income due to invalid date format. Ensure that it follows dd/MM/yyyy.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
