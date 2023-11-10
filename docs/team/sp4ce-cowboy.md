---
layout: page
title: Rubesh Suresh's Project Portfolio Page
---

### Project: UniCa$h

UniCa$h is a desktop application for university students who want to be more financially conscious.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. UniCa$h is designed
for users who are proficient with CLI. It is written in Java 11, spanning about [20 KLoC](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T16-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=AY2324S1-CS2103-T16-3%2Ftp%5Bmaster%5D&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&isTabOnMergedGroup=true&tabOpen=true&tabType=authorship&tabAuthor=elhy1999&tabRepo=AY2324S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=true&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).
My contributions are given below. Code contributed by me: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T16-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=sp4ce-cowboy&tabRepo=AY2324S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Functional Features**

* **Delete Transaction Command**: [PRs: [#62](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/62)]
  {_**Credits:** AB3's `Delete` command._}
  * **What it does:** A CLI command to remove a specified transaction from UniCa$h.
  * **Justification:** Core utility of a Finance Management App.

* **Clear Transactions Command**: [PRs:
[#63](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/63)
[#138](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/138/)]
  {_**Credits:** AB3's `Clear` command._}
  * **What it does:** A CLI command to remove all transactions, and a parser class.
  * **Justification:** To allow the user to start on a blank slate. Parser ensures that
  mass-deletion is intentional, adding a layer of safety.
  

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
  * _**Note:** The Help Command has been enhanced by [Lip Wei](https://github.com/lipwei1808)
        as mentioned [here](lipwei1808.md)._
 
* **Get Transaction Command** [PRs: 
[#101](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/101)]
  * **What it does:** A CLI Command to see expanded details of a specific transaction.
  * **Justification:** To allow a user to see details of a transaction whose properties' lengths
  can extend beyond the UI's ability to display them.

   
* **Find Transactions Command Enhancement**: [PRs:
  [#101](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/101)
  [#138](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/138)]
  * **What it does:** A CLI command that allows the user to search for specific transactions based
  on certain keywords.
  * **Justification:** To allow the user to search for specific transactions especially if there are
  many transactions stored.
  * **Highlights:** Complements the Get Command and the Rolling Balance Indicator.
  * **Possible Future Additions:** The Find command is limited to Name, Category and Location. This
  can be expanded to include the other properties in the future.
  * _Note: The initial Find Command was implemented by [Jamie](https://github.com/jamz903)
  as mentioned [here](jamz903.md)_

**User Interface Features**

* **User Interface Overhaul & Enhancement** [PRs:
[#78](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/78)
[#82](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/82)
[#86](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/86)
[#94](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/94)
[#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * The UI of UniCa$h has been enhanced with an adjustable vertical SplitPane,
  a larger `Results Display Box`, a reversed `Transactions List Panel`, colored 
  currencies representation, and other minor UI modifications.
  * Justification: To better fit our GUI needs corresponding to the visual representation
  of our implemented features for UniCa$h.
  * _Note: The original UI transition was done by [Jamie](https://github.com/jamz903) as mentioned [here](jamz903.md)_

* **Rolling Balance Indicator** [PRs: [#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * Added to the `StatusBarFooter` of the UniCa$h UI to explicitly indicate the current balance of any group
  of transactions. At start-up the user will see their net total balance. With certain commands like `find`, the
  the user will see their net total expenditure for a group of transactions.
  * **Justification:** For the user to get a holistic sense of their current financial state and
    quantitatively determine certain spending patterns in related transactions.


* **Command Box History & Enhancements** [PRs:
[#110](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/110)
[#119](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/119)]
  * The `CommandBox` is made to remember the user's previous inputs, up to a
  maximum of 10 previous inputs. The user can use the arrow keys to traverse through their history
  of inputs, and press the `ESC` to clear the command box.
  * **Justification:** To provide our CLI-proficient targe users a more intuitive,
  convenient and efficient means for them to interact with UniCa$h, similar to that of a real CLI.


* **StyleSheet Class** [PRs: [#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * A StyleSheet class was created such that all components involving any form of graphical
  representation have a centralized source of reference.
  * **Justification:** To provide a seamless visual experience across UniCa$h for a better user-experience.


* **Hash-based Category Coloring** [PRs: [#96](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/96)]
  * This feature allows for a consistent color pattern across unique categories without having to hard-code colors, as 
  there is no limit to total category count.
  * **Justification:** Each category having the same specific color assigned to it would lead to better
  visual consistency and therefore, better overall user experience.


_**Addendum**: The focus on color consistency and user experience across UniCa$h is motivated by certain scientific
[studies](https://www.sciencedirect.com/science/article/abs/pii/S0167811623000599]) that show a positive 
correlation between color complexity and user engagement. Additionally,
[this](https://www.diva-portal.org/smash/record.jsf?pid=diva2%3A1788177&dswid=-2283) study also found out
that certain design elements contribute to increased user engagement especially by young adults. Given that
our target users (university students) are mostly young adults, the UI components are intended to increase
target user engagement with UniCa$h, which would indirectly lead to overall improvements in their personal financial
management, despite not being direct CLI features._

**Decommissioned Features**

Below are some of the features that I had implemented prior, but were removed due to various project constraints.

* **Local User Guide**: [PRs: [#82](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/82)]
  * **What it was:** A PDF version of the user guide (UG) that the user can access directly from UniCa$h
  * **Motivation for Implementation:** Given the NFR of offline usage, it would be useful for the User to
  have local access to the UG.
  * **Justification for Removal:** It depended on the host machine to display the PDF, which was unreliable, and
  difficult to test. Furthermore, we were allowed to assume that all users have access to the UG.

* **Test Command**: [PRs: [#126](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/126)]
  * **What it was:** A temporary Test Command intended for internal testing of UniCa$h.
  * **Motivation for Implementation:** To test UniCa$h's GUI and functionality by populating it with a
  lot more transactions than the default UniCa$h.
  * **Justification for Removal:** Merge onto a separate testing branch instead, which I also opened.

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


**Minor Contributions:**

* **UniCash Messages** - A class to encapsulate and consolidate messages displayed in a central location.
[PR: [#62](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/62)]
* **Package Refactoring** - Refactored the codebase to refer to UniCash instead of AB3. [PR: [#77](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/62)]
* **Overall Aesthetics** - Refactored the GUI to use the [Inter](https://fonts.google.com/specimen/Inter) font
  (credits to [Jamie](jamz903.md) for finding the font) and a custom wallet icon.
  [PR: [#78](https://github.com/AY2324S1-CS2103-T16-3/tp/pull/78)]

**Documentation**
* User Guide:
  * Added documentation for own features (Delete, Clear, Reset, Find, Get, Help Commands)
  * UI Features and Overview of UI
* Developer Guide:
  * Migrated details of the Developer Guide from an external source (Google Docs) and consolidated them in GitHub.
  * Set up structure skeleton for Developer Guide for easy and structured documentation by the team.
  * Add documentation for own features (Delete, Clear, Reset, Find, Get, Help Commands)
  * Add documentation for UI and UI features

**Project Management**
* Created and initialized the CS2103-T16-3 organization on GitHub
* Created a testing branch to test new features in UniCa$h
* Managed release v1.3a on GitHub - Can be found [here](https://github.com/AY2324S1-CS2103-T16-3/tp/releases/tag/v1.3).
* Made and released demo video for release v1.3b [here](https://github.com/AY2324S1-CS2103-T16-3/tp/releases/tag/v1.3b)

**Community**
* **Pull Requests Reviewed**: Refer to [here](https://github.com/AY2324S1-CS2103-T16-3/tp/pulls?q=is%3Apr+reviewed-by%3Asp4ce-cowboy)
[Reviewed 41 PRs, with 91 PR comments in total thus far.]
* **Pull Requests Opened**: Refer to [here](https://github.com/AY2324S1-CS2103-T16-3/tp/pulls?q=is%3Apr+author%3Asp4ce-cowboy+)
[Opened 33 PRs. Almost all major PRs have full details and screenshots.]
* **Issues Assigned & Completed**: Refer to [here](https://github.com/AY2324S1-CS2103-T16-3/tp/issues?q=is%3Aissue+author%3Asp4ce-cowboy+is%3Aclosed+)


