---
layout: page
title: Rubesh Suresh's Project Portfolio Page
---

## Project: UniCa$h

UniCa$h is a desktop application for university students who want to be more financially conscious.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. UniCa$h is designed
for users who are proficient with the CLI. UniCa$h is written in Java 11, spanning about [20 KLoC](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T16-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=AY2324S1-CS2103-T16-3%2Ftp%5Bmaster%5D&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&isTabOnMergedGroup=true&tabOpen=true&tabType=authorship&tabAuthor=elhy1999&tabRepo=AY2324S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=true&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

---

### My Contributions


### Functional Features

* **Delete Transaction Command**: [PRs: [#62](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/62)]
  * **What it does:** A CLI command to remove a specified transaction from UniCa$h.
  * **Justification:** Core utility of a Finance Management App.
  * **Credits:** AB3's implementation of the `Delete` command


* **Clear Transactions Command**: [PRs:
[#63](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/63)
[#138](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/138/)]
  * **What it does:** A CLI command to remove all transactions from UniCa$h, and 
  a parser class
  * **Justification:** To allow the user to start on a blank slate. Parser ensures that
  mass-deletion is intentional, adding a layer of safety.
  * **Credits:** AB3's implementation of the `Clear` command.


* **Reset UniCash Command**: [PRs:
[#90](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/90)
[#138](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/138/)]
  * **What it does:** A CLI Command to restore UniCa$h to its original state with
  the same default transactions that appear when the app is opened for the first time.
  * **Justification:** An easy way for users and testers of the application alike to re-start
  using UniCa$h from its default state.
  
  
* **Help Command & UniCash Welcome Window** [PRs:
  [#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * **What it does:** A CLI command to open the Help Window with the 
  URL to UniCa$h's user guide, and also display a Message with a collated list of commands available,
  which is also set as the start-up Welcome Window.
  * **Justification:** To allow the user quick access to an overview of commands available.
  * **Credits:** AB3's implementation of the `Help` command, partially.
  * _**Note:** The Help Command has been enhanced by [Lip Wei](https://github.com/lipwei1808)
        as mentioned [here](lipwei1808.md)._

    
* **Get Transaction Command** [PRs: 
[#101](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/101)]
  * **What it does:** A CLI Command to see expanded details of a specific transaction.
  * **Justification:** To allow a user to see details of a transaction whose properties' lengths
  can extend beyond the UI's ability to display them.
  * **Highlights:** This Command complements the Find command where a user can
  search for a keyword with the Find Command and retrieve details of a transaction with the Get Command. The 
  Find Command alters the visible Transactions List Panel, but the Get Command does not!

    
* **Find Transactions Command Enhancement**: [PRs:
  [#101](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/101)
  [#138](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/138)]
  * **What it does:** A CLI command that allows the user to search for specific transactions based
  on certain keywords.
  * **Justification:** The user might accumulate many transactions over a prolonged period of time
  using the app, and this command allows the user to search for specific transactions.
  * **Highlights:** This command complements the Get Command and the Rolling Balance Indicator.
  * **Possible Future Additions:** The Find command is limited to Name, Category and Location. This
  can be expanded to include the other properties in the future.
  * _Note: The initial Find Command was implemented by [Jamie](https://github.com/jamz903)
  as mentioned [here](jamz903.md)_

---

### User Interface Features

* **User Interface Overhaul & Enhancement** [PRs:
[#78](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/78)
[#82](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/82)
[#86](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/86)
[#94](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/94)
[#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * The UI of UniCa$h has been enhanced with an adjustable vertical SplitPane,
  a larger `Results Display Box`, a reversed `Transactions List Panel`, color-coded 
  signed representation of currencies, and other minor UI modifications.
  * Justification: To better fit our GUI needs for UniCa$h such that the
  implementation of our chosen features and their corresponding visual
  representation would be appropriate both in the context of our target user 
  and the utility of the features themselves.
  * _Note: The original transition from AB3 to UniCa$h's UI was done by
  [Jamie](https://github.com/jamz903) as mentioned [here](jamz903.md)_


* **Rolling Balance Indicator** [PRs: [#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * A rolling balance indicator is added to the `StatusBarFooter` of the UniCa$h UI
  that can be used to visually indicate the current balance of any group
  of transactions. At start-up and by default, the user will be able to see
  their net total balance. Upon the usage of certain commands like `find`, the
  the user will be able to see their total expenditure for a group of expenses,
  total earned income for a group of incomes, or a net total expenses for a mixture
  of both.
  * **Justification:** With the total balance, the user will be able to get a holistic 
  sense of their current financial state. Additionally, the user will also be able to
  determine quantitatively certain spending patterns in associated or related transactions, thereby
  having a better understanding of their own financial habits.


* **Command Box History & Enhancements** [PRs:
[#110](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/110)
[#119](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/119)]
  * The `CommandBox` is made to remember the user's previous inputs, up to a
  maximum of 10 previous inputs, and detect the `ESC` key. The user can, at any point in time,
  use the arrow keys to traverse through their history of inputs, and press the `ESC` to clear
  the command box.
  * **Justification:** Given our target users being CLI-proficient, we want to
  provide a more intuitive and convenient means for them to interact with
  UniCa$h similar to that of a real CLI, such that they can carry out tasks
  requiring repeated usage of the same command in a much more efficient way,
  amongst other benefits of a real CLI experience.
  

* **StyleSheet Class** [PRs: [#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * To provide a seamless visual experience across UniCa$h, a StyleSheet class 
  was created such that all components involving any form of visual or graphical
  representation have a centralized and singular source of reference. 
  * **Justification:** To provide a seamless and unified visual theme across UniCa$h
  and thereby facilitate a better user-experience.
  * **[Possible Future Addition]:** The universality and extensibility of the StyleSheet class is such that it
  is possible to allow the user themselves to create and select certain custom themes
  which can then be applied across all visual elements in UniCa$h. 


* **Hash-based Category Coloring** [PRs: [#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * A sub-set of the StyleSheet class, this feature allows for a consistent color pattern
  across categories. In UniCa$h, the user is not restricted to any number of categories
    (in total). Thus, it is not possible to hard-code any color value into every single category.
    The hash-based color generation ensures that every unique category has a unique color assigned to
    it, without each of the color values having to be pre-determined and assigned manually.
  * **Justification:** Each category having the same specific color assigned to it would lead to better
  visual consistency and therefore, better overall user experience.


**Addendum**: Especially for this feature and the above-mentioned StyleSheet class, the focus on colors
 consistency and user experience consistency across UniCa$h is motivated by the fact that certain scientific
[studies](https://www.sciencedirect.com/science/article/abs/pii/S0167811623000599]) have shown a positive 
correlation between color complexity and user engagement. Additionally,
[this](https://www.diva-portal.org/smash/record.jsf?pid=diva2%3A1788177&dswid=-2283) study also found out
that certain design elements contribute to increased user engagement especially by young adults. Given that
our target users (university students) are mostly young adults, The UI components are intended to increase
target user engagement with UniCa$h, which would indirectly lead to overall improvements in their personal financial
management, despite not being direct CLI features in and of themselves.

---

### Decommissioned Features

Below are some of the features that I had implemented prior, but were removed due to various project constraints.

* **Local User Guide**: [PRs: [#82](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/82)]
  * **What it was:** A PDF version of the user guide was packaged into the UniCa$h jar file, and the user
  would be able to click on a UI Menu button to open the User Guide locally. Additionally, a command word was
  planned to be implemented (`show_userguide`) that would open the User Guide with CLI input.
  * **Motivation for Implementation:** Given one of the UniCa$h's non-functional requirements (NFR) of offline usage,
  it would be useful for the User to have immediate and local access to the User Guide, as opposed to having to open the
  help window and follow the link to the User Guide online.
  * **Justification for Removal:** The functionality depended on the host machine to display the PDF file, and given
  another one of our NFRs of cross-platform support, it was not possible to confidently ascertain the host machine's ability to
  display the PDF file. In the context of the project, we can assume that all Users already have direct access to our
  User Guide. Thus, this feature was deemed unnecessary.


* **Test Command**: [PRs: [#126](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/126)]
  * **What it was:** A temporary Test Command intended for internal testing of UniCa$h.
  * **Motivation for Implementation:** To test UniCa$h's GUI and functionality with a lot more transactions
  than the default UniCa$h. The default UniCa$h presented by the Reset Command would be in a reasonable and visually way
  as it is intended for new users to get an understanding of the App. However, to test various edge cases, we wanted
  to use a separate Test command that would populate UniCa$h with these transactions.
  * **Justification for Removal:** To ensure safety of the main codebase, it was best to not include
  any additional commands not intended for production as removing them might introduce previously
  unaccounted bugs.


* **Fuzzy-Find Transactions**: [PRs: [#110](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/110)]
  * **What it was:** This was the initial implementation of the existing Find Command. As opposed to being limited to Name,
  Location, and Category, it also searched all properties including DateTime, Type, and Amount. Furthermore,
  as opposed to having to meet all keywords, the presence of any keyword(s) would be considered a match, intending
  to act as a logical `OR` for all keywords.
  * **Motivation for Implementation:** A user would be able to search for any keyword across any property without having
  to specify any prefixes or parameters, emulating a fuzzy-finding functionality. This would greatly improve transaction
  retrieval speed.
  * **Justification for Removal:** The feature is too expansive in its breadth of search, and initial users might feel
  overwhelmed or confused by its functionality. Furthermore, its expansive nature might be outside the scope of UniCa$h, 
  on top of other functional constraints like insufficient testability.


* **Filter Transactions**: [PRs: [#130](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/130)]
  * **What it was:** This feature was intended to complement the Fuzzy-Find transaction feature. While the Fuzzy-Find
  acted like a logical `OR` amongst keywords, Filter was intended to act as a logical `AND`, a direct inverse analogue.
  * **Motivation for Implementation:** Combined with the Fuzzy-Find feature, the user would have extreme precision
  and control over their search results, being able to both narrow down and broaden the scope of transactions as much
  as possible. Combined with the Rolling Balance Indicator, the user would have the means to tabulate total expenses for
  every conceivable combination of transaction properties.
  * **Justification for Removal:** Similar to the Fuzzy-Find feature, Filter is too expansive in its breadth of
  implementation and initial users might feel overwhelmed or confused by its functionality. Similarly,
  Filter's expansive nature might be outside the scope of UniCa$h,

_Both features were simplified and integrated into the existing Find Command.
[PR: [#138](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/138)]_




#### Minor Contributions:

Here are some minor features/contributions that I have made. Note that some of these features have been
augmented by the entire team.

* **UniCash Messages** - A class to encapsulate and consolidate messages displayed in a central location.
[PR: [#62](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/62)]
* **Package Refactoring** - Refactored the codebase to refer to UniCash instead of AB3. [PR: [#77](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/62)]
* **Overall Aesthetics** - Refactored the GUI to use the [Inter](https://fonts.google.com/specimen/Inter) font
  (credits to [Jamie](jamz903.md) for finding the font) and a custom wallet icon.
  [PR: [#78](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/78)]

#### Code
* Code contributed: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T16-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=sp4ce-cowboy&tabRepo=AY2324S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Documentation
* User Guide:
    * Added documentation for own features
      * Delete, Clear, Reset, Find, Get, Help Commands
      * UI Features and Overview of UI
* Developer Guide:
  * Migrated details of the Developer Guide from an external source (Google Docs) and consolidated them in GitHub.
  * Set up structure skeleton for Developer Guide for easy and structured documentation by the team.
  * Add documentation for own features
    * Delete, Clear, Reset, Find, Get, Help Commands
  * Add documentation for UI and UI features
  
  
#### Project Management
* Created and initialized the CS2103-T16-3 organization on GitHub
* Managed release v1.3a on GitHub - Can be found [here](https://github.com/AY2324S1-CS2103-T16-3/tp/releases/tag/v1.3).
* Made and released demo video for release v1.3b [here](https://github.com/AY2324S1-CS2103-T16-3/tp/releases/tag/v1.3b)

#### Community
* **Pull Requests Reviewed**: Refer to [here](https://github.com/AY2324S1-CS2103-T16-3/tp/pulls?q=is%3Apr+reviewed-by%3Asp4ce-cowboy)
  * Reviewed 41 PRs, with 91 PR comments in total thus far.
* **Pull Requests Opened**: Refer to [here](https://github.com/AY2324S1-CS2103-T16-3/tp/pulls?q=is%3Apr+author%3Asp4ce-cowboy+)
  * Opened 33 PRs, of which 28 were merged to the main codebase.
* **Issues Assigned & Completed**: Refer to [here](https://github.com/AY2324S1-CS2103-T16-3/tp/issues?q=is%3Aissue+author%3Asp4ce-cowboy+is%3Aclosed+)


