---
layout: default.md
title: "User Guide"
pageNav: 3
---

# User Guide

Welcome Financial Advisors! 👋 This guide will help you get started with FinClient.

Are you tired of juggling multiple client records and struggling to keep track of their preferences? FinClient is designed for tech-savvy financial advisors like you who prefer typing and need fast, efficient access to client details. 💡 With FinClient, managing hobbies, preferences, financial packages, and contact information has never been easier!

FinClient is a Command-Line Interface (CLI) based application, or simply put, a typing-based tool with specific features such as:

* 🚀 Adding and deleting contacts
* 🛠 Editing your contact's details
* 🔍 Locating your contacts by name
* 🏢 Maintaining contact-specific details such as jobs and workplaces
* 📊 Sorting your contacts by name, net worth, order prices, and amounts
* 🙈 Hiding and revealing contacts for privacy
* 📈 Estimating Call Auction clearing prices simultaneously

You can look forward to an efficient and enjoyable experience with FinClient!

## Table of Contents
- [Quick start](#quick-start)
- [Features](#features)
    - [Viewing help](#viewing-help)
    - [Adding a person](#adding-a-person)
    - [Listing all persons](#listing-all-persons)
    - [Editing a person](#editing-a-person)
    - [Locating persons by name](#locating-persons-by-name)
    - [Deleting a person](#deleting-a-person)
    - [Hiding a person](#hiding-a-person)
    - [Revealing a person](#revealing-a-person)
    - [Limit orders and Call Auction calculator](#order)
    - [Adding remarks](#adding-remarks)
    - [Sorting contacts](#sorting-contacts)
    - [Clearing all entries](#clearing-all-entries)
    - [Exiting the program](#exiting-the-program)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
    - [Archiving data files](#archiving-data-files)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## <span id="quick-start">Quick start</span>

1. Ensure you have Java `17` or above installed on your computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-T11-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FinClient.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar finclient.jar` command to run the application.<br>
   A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type a command in the command box and press Enter to execute it. For example, typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the app.

1. Please refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## <span id="features">Features</span>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times, including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order. However, to add deadlines to remarks, the parameter must be directly behind the remark parameter<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable. <br>
  e.g. if the command requires `r/REMARKS by/TIME`, `by/TIME r/REMARKS` is not acceptable. Similarly, there should not any parameters between these 2 parameters

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### <span id="viewing-help">Viewing help : `help`</span>

Displays a pop-up window with a scrollable help message. When scrolled to the bottom, a button to copy a URL link to this guide appears.

![help message](images/helpMsg.png)

Format: `help`

### <span id="adding-a-person">Adding a person : `add`</span>

Adds a person to FinClient.

Format: `add n/NAME p/PHONE_NUMBER [p/PHONE_NUMBER] [p/PHONE_NUMBER] e/EMAIL a/ADDRESS [r/REMARKS [by/TIME]] [c/COMPANY] [j/job] [s/STOCKPLATFORM] [$/NETWORTH] [t/TAG]…​ `

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**
A person can have any number of tags (including 0).<br>
:bulb: **Tip:**
Optional fields can be left out when adding a person's contact.
</div>

Examples:
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 r/10 year sentence t/criminal `

### <span id="listing-all-persons">Listing all persons : `list`</span>

Shows a list of all persons in FinClient.

Format: `list`

### <span id="editing-a-person">Editing a person : `edit`</span>

Edits an existing person in FinClient.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK [by/TIME]] [t/TAG]…​ [c/COMPANY] [j/job] [s/STOCKPLATFORM] [$/NETWORTH]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e., adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* For the optional fields of company, job, stock platform, and net worth, you can remove the existing value by typing their prefix followed with a delete.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 2 j/delete c/LittleStartUp s/delete $/delete` Edits the job of the 2nd person to be `LittleStartUp` and clears the existing job, stock platform, and net worth.

### <span id="locating-persons-by-tag">Locating persons by tag: `tag`</span>

Finds persons whose tags contain any of the given keywords.

### <span id="locating-persons-by-name">Locating persons by name: `find`</span>

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g., `hans` will match `Hans`.
* The order of the keywords does not matter. e.g., `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g., `Han` will not match `Hans`.
* Persons matching at least one keyword will be returned (i.e., `OR` search).
  e.g., `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `find John` returns `John` and `John Doe`.
* `find alex david` returns `Alex Yeoh`, `David Li`.<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### <span id="deleting-a-person">Deleting a person : `delete`</span>

Deletes the specified person from FinClient.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive number displayed beside a contact's name** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in FinClient.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### <span id="hiding-a-person">Hiding details : `hide`</span>

Hides the details of the specified person in FinClient.

Format: `hide all|INDEX|name`

* Hides the details of the person specified at `INDEX` or by `name`.
* Hides all contacts' details if `all` is used.
* The index **must be a positive number displayed beside a contact's name** 1, 2, 3, …​

Examples:
* `hide 2` hides the 2nd person in FinClient.
* `find Betsy` followed by `hide 1` hides the 1st person in the results of the `find` command.
  ![example of a hidden person](images/hidden_person.png)

### <span id="revealing-a-person">Revealing details : `reveal`</span>

Reveals the details of the specified person in FinClient.

Format: `reveal all|INDEX|name`

* Reveals the details of the person specified at `INDEX` or by `name`.
* Reveals all contacts' details if `all` is used.
* The index **must be a positive number displayed beside a contact's name** 1, 2, 3, …​

Examples:
* `reveal 2` reveals the 2nd person in FinClient.
* `find Betsy` followed by `reveal 1` reveals the 1st person in the results of the `find` command.

### <span id="order">Limit orders and Call Auction calculator : `order`</span>

Records a limit order for a specific contact in FinClient.

The [call auction](https://www.investopedia.com/terms/c/call-auction.asp) calculator uses the aggregated orders to determine a clearing price.

Format: `order INDEX [o/TYPE am/AMOUNT at/PRICE]`

* Records a limit order for the person specified at `INDEX`.
* The index **must be a positive number displayed beside a contact's name** 1, 2, 3, …​
* If only the index is provided, the order will be recorded as an empty order.
* The order type can be `BUY` or `SELL`.
* The amount must be a positive integer.
* The price must be a positive decimal number.

Examples:
* `order 1` records an empty order for the person listed at index 1.
* `order 1 o/buy am/10 at/9.50` records a buy order of 5 units at $5.50 for the person listed at index 1.
* `order 1 o/sell am/10 at/10.50` records a sell order of 10 units at $10.50 for the person listed at index 1.

<div markdown="span" class="alert alert-primary">

:information_source: **Design choice:**
Since FinClient is designed for a single financial instrument, every order recorded relates to that same asset.
</div>

### <span id="adding-remarks">Adding remarks: `remark`</span>

Adds a remark to the specified person in FinClient.

Format: `remark INDEX r/[REMARKS] [by/TIME]`

* Adds a remark to the person specified at `INDEX`.
* Including the time will create a deadline for the current remark being added to the contact.

Examples:
* `remark 1 r/this is a test remark` adds `this is a test remark` to the remark section of the contact listed at index 1.
* `remark 1 r/Set buy order by/2025-10-15` adds `Set buy order` to the remark section of the contact listed at index 1 along with a deadline. <br> As the date nears, there will be a reminder shown on the main interface.

### <span id="sorting-contacts">Sorting contacts: `sort`</span>

Sorts the contact list based on the criteria of name or phone number.

Format: `sort CRITERIA`

Examples:
* `sort name` sorts the contact list in FinClient based on contact's name.
* `sort networth` sorts the contact list in FinClient based on contact's net worth bracket.
* `sort amount` sorts the contact list in FinClient based on contact's order amount.
* `sort price` sorts the contact list in FinClient based on contact's order price.

### <span id="clearing-all-entries">Clearing all entries : `clear`</span>

Clears all entries from the address book.

Format: `clear`

### <span id="exiting-the-program">Exiting the program : `exit`</span>

Exits the program.

Format: `exit`

### <span id="saving-the-data">Saving the data</span>

Your FinClient data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### <span id="editing-the-data-file">Editing the data file</span>

Your FinClient data is saved automatically as a JSON file `[JAR file location]/data/finclient.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**
If your changes to the data file make its format invalid, FinClient will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause FinClient to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### <span id="archiving-data-files">Archiving data files `[coming in v2.0]`</span>

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## <span id="faq">FAQ</span>

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FinClient home folder.

--------------------------------------------------------------------------------------------------------------------

## <span id="known-issues">Known issues</span>

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## <span id="command-summary">Command summary</span>

 Action | Format, Examples
------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 **Add** | `add n/NAME p/PHONE_NUMBER [p/PHONE_NUMBER] [p/PHONE_NUMBER] e/EMAIL a/ADDRESS [r/REMARK] [t/TAG]…​ [c/COMPANY] [j/job] [s/STOCKPLATFORM] [$/NETWORTH] ` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
 **Clear** | `clear`
 **Delete** | `delete INDEX`<br> e.g., `delete 3`
 **Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/REMARK] [t/TAG]…​ [c/COMPANY] [j/job] [s/STOCKPLATFORM] [$/NETWORTH] `<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
 **Remove Optional Fields** | `edit INDEX [t/] [j/delete] [c/delete] [s/delete] [$/delete]` <br> e.g., `edit 2 t/ j/delete`
 **Adding/Editing/Removing Remarks and Deadlines** | `remark INDEX r/[REMARK] [by/TIME]`<br> e.g., `remark 1 r/Likes to swim. by/2020-10-15 18:00`<br> e.g., `remark 1 r/`
 **List** | `list`
 **Help** | `help [COMMAND]`<br> e.g., `help add`
 **Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
 **Hide** | `hide all` or `hide INDEX` or `hide name`
 **Reveal** | `reveal all` or `reveal INDEX` or `reveal name`
 **Sort** | `sort CRITERIA` <br> e.g., `sort name` or `sort networth` or `sort price` or sort `sort amount`
 **Order** | `order INDEX o/TYPE am/AMOUNT at/PRICE` <br> e.g., `order 1 o/buy am/5 at/5.50`
 **List** | `list`
 **Help** | `help`
