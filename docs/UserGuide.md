
# Vendor Vault User Guide

Vendor Vault is a **desktop app for managing supplier contact information and deliveries, optimized for use via a  Line Interface** (CLI). If you can type fast, VendorVault can get your contact management tasks done faster than traditional GUI apps. Vendor Vault is targeted at small convenience/grocery stores. 

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer. If you are unsure, you can check by opening the command terminal and typing `java -version`. If you do not have Java installed, you can download it from [here](https://www.oracle.com/java/technologies/downloads/#java17?er=221886).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S1-CS2103T-W14-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your VendorVault Application.

1. Open a command terminal, type `cd [path-to-your-folder]` (path to the folder you put the jar file in) and use the `java -jar vendorvault.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add -s n/John Doe p/98765432 e/johnd@example.com com/John street, block 123, #01-01 pro/ iPhone` : Adds a supplier named `John Doe` to the VendorVault.

   * `delete -d 3` : Deletes the 3rd contact shown in the current delivery list.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.
<br>
--------------------------------------------------------------------------------------------------------------------

# Features
<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help` `clear` and `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123 `, it will be interpreted as `exit`.

* For all parameters, starting and ending spaces are trimmed.

* For all commands except `find` and `sort`, the displayed list of suppliers/deliveries will be the unfiltered and unsorted list of all suppliers/deliveries.<br>
  For `find` and `sort` commands, the displayed list will be the corresponding filtered/sorted list of supplier/deliveries.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>
---

## Command table of content

### Supplier Commands

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**[Add](#adding-a-supplier-add-s)**    | `add -s n/NAME p/PHONE e/EMAIL com/COMPANY [t/TAG]…​ [pro/PRODUCT]…​` <br> e.g., `add -s n/John Doe p/98765432 e/johnd@example.com com/companyA t/friends t/owesMoney pro/rice pro/bread`
**[Delete](#deleting-a-supplier-delete-s)** | `delete -s INDEX`<br> e.g., `delete -s 3`
**[List](#listing-all-suppliers-list-s)**   | `list -s`
**[Mark](#mark-a-supplier-with-a-status-mark-s)**   | `mark -s INDEX STATUS`<br> e.g.,`mark -s 2 active`
**[Find](#find-a-supplier-find-s)**   | `find -s n/<KEYWORD FOR SUPPLIER NAME> com/<KEYWORD FOR SUPPLIER COMPANY> pro/<KEYWORD FOR SUPPLIER PRODUCT>` <br> e.g., `find -s n/link com/NU`
**[Sort](#sort-suppliers-sort-s)**   | `sort -s so/SORT_ORDER sb/SORT_BY_FIELD`<br> e.g., `sort -s so/a sb/n`


### Delivery Commands

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**[Add](#adding-a-delivery-add-d)**    | `add -d on/DELIVERY_DATE_TIME s/SUPPLIER_INDEX pro/PRODUCT q/QUANTITY kg/g/L/mL/units c/COST` <br> e.g., `add -d on/18-06-2024 17:00 s/1 pro/bread q/500 g c/5.50`
**[Delete](#deleting-a-delivery-delete-d)** | `delete -d INDEX`<br> e.g., `delete -d 3`
**[List](#listing-all-deliveries-list-d)**   | `list -d`
**[Mark](#marking-a-delivery-mark-d)**   | `mark -d INDEX STATUS`<br> e.g.,`mark -d 2 PENDING`
**[Find](#find-a-delivery-find-d)**   | `find -d on/DELIVERY_DATE_TIME stat/STATUS s/SUPPLIER_INDEX pro/PRODUCT`<br> e.g., `find -d on/ 28-06-2025 17:00 pro/ milk`
**[Sort](#sort-deliveries-sort-d)**   | `sort -d so/SORT_ORDER sb/SORT_BY_FIELD`<br> e.g., `sort -d so/a sb/c`
**[Upcoming](#upcoming-deliveries-upcoming)** | `upcoming aft/START_DATE bef/END_DATE`<br> e.g., `upcoming aft/19-12-2022 08:00 bef/18-06-2023 17:00`



### General Commands
Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**[List](#viewing-all-deliveries-and-suppliers-list)**   | `list -a`
**[Clear](#clearing-all-suppliers-and-deliveries-clear)**   | `clear`
**[Help](#viewing-help-help)**   | `help`
**[Exit](#exiting-the-program-exit)**   | `exit`



---
### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

##### Here's how it would look like in the app:

![help message](images/helpMessage.png)

### Viewing all deliveries and suppliers : `list -a`

Lists all suppliers and deliveries in the VendorVault

Format: `list -a`

<box type="warning" seamless>

**Warnings**:
- No other parameters should be given for this command.
- At least one space between list and -a
</box>

### Clearing all suppliers and deliveries : `clear`

Clear all data regarding suppliers and deliveries in vendor vault

Format: `clear`

---

## <ins> Supplier Commands </ins>

### Adding a supplier: `add -s`

Adds a supplier to VendorVault.

Format: `add -s n/NAME p/PHONE e/EMAIL com/COMPANY [t/TAG]…​ [pro/PRODUCT]…​`

<box type="details" seamless>

Parameters:

- `n/NAME`: `NAME` is the supplier's name. It must be alphanumeric, and cannot be blank.
- `p/PHONE`: `PHONE` is the supplier's phone number. It must be numeric, and contain at least 3 digits.
- `e/EMAIL`: `EMAIL` is the supplier's email address. It must be in a valid email address format, and cannot be blank.
  - Please see [below](#valid-email-address-format) for more information on what constitutes a valid email address format.
- `com/COMPANY`: `COMPANY` is the company associated with the supplier. It must be in a valid company name format, and cannot be blank.
  - Please see [below](#valid-company-name-format) for more information on what constitutes a valid company name format.
- `[t/TAG]`: `TAG` is the tag(s) associated with the supplier. It must be alphanumeric, and only contain between 1 and 50 (inclusive) characters.
- `[pro/PRODUCT]`: `PRODUCT` is the product(s) associated with the supplier. It must be alphanumeric, only contain between 1 and 50 (inclusive) characters, and spaces are also allowed.
  - One space is counted as one character.
  - A product name cannot be made up of only spaces.

**Tip:** A supplier can have any number of `TAG` and `PRODUCT` (including 0).
- To include multiple `TAG`, use multiple `t/TAG`.

</box>

<box type="warning" seamless>

**Warnings**:
- At least one space is needed between `add` and `-s`.
- A warning will be given if the user tries to add a duplicate supplier.
- A supplier is considered duplicate if they have the same `NAME` and `COMPANY`.
  - Comparison between different `NAME`is case-sensitive.
  - Comparison between different `COMPANY` is case-insensitive.
- Adding duplicate `TAG`/`PRODUCT` will result in only one copy being added to the supplier.
  - Comparison between different `TAG`/`PRODUCT` is case-sensitive.
- A supplier has a default `STATUS` of `active`.

</box>

Examples:
- `add -s n/John Doe p/98765432 e/johnd@example.com com/companyA t/friends t/owesMoney pro/rice pro/bread`
- `add -s n/Betsy Crowe p/98223232 e/betsycrowe@example.com com/Newgates t/urgent pro/soap`

Expected output:
- `New supplier added: John Doe; Phone: 98765432; Email: johnd@example.com; Company: companya; Tags: [owesMoney][friends]; Products: [bread][rice]; Status: active`
- `New supplier added: Betsy Crowe; Phone: 98223232; Email: betsycrowe@example.com; Company: newgates; Tags: [urgent]; Products: [soap]; Status: active`

#### Here's how it would look like in the app:
TO UPDATE IMAGE AFTER FINAL UPDATE TO APPLICATION!!!
![add Command](images/addSupplierCommand.png)

#### Valid email address format

Valid email addresses are of the format: `local-part@domain`.  
Both `local-part` and `domain` must start and end with alphanumeric characters.  
Special characters cannot be used consecutively.

`local-part` must be alphanumeric, or these special characters: `+.-_`.

`domain` must be alphanumeric, or these special characters: `.-`.  
`domain` can be separated into multiple parts with `.`, and the last part must be at least 2 alphanumeric characters.  
- Each part must start and end with alphanumeric characters.
- e.g. `example.com` is separated into two parts, `example` and `com`, and is a valid `domain`.

Valid email address examples:
- john.doe@example.com
- user123@my-website.com
- first.last@school.edu.sg

Invalid email address examples:
- john.@example.com (`local-part` (i.e. `john.`) cannot end in a `.`)
- john--doe@example.com (Special character `-` cannot be used consecutively)
- john.doe@example.c (Final `domain` part (i.e. `c`) must have at least 2 alphanumeric characters)

#### Valid company name format

Valid company names must be alphanumeric or punctuation characters, and spaces are allowed.  
Punctuation characters include ``!"#$%&'()*+,-./:;<=>?@[\]^_\\`{|}~``

### Listing all suppliers: `list -s`

Shows a list of all suppliers in VendorVault. The delivery list will not be affected.

Format: `list -s`

<box type="warning" seamless>

**Warnings**:
- At least one space is needed between `list` and `-s`.
- No other parameters should be given for this command.

</box>

### Deleting a supplier : `delete -s`

Deletes a supplier from the list of suppliers in VendorVault.

Format: `delete -s INDEX`

<box type="details" seamless>

Parameters:

- `INDEX`: The index of the supplier to be deleted in the displayed list. Must be a positive numeric number.

</box>

<box type="warning" seamless>

**Warnings**:
- At least one space is needed between `list` and `-s`.
- Only one supplier can be deleted by one command.

</box>

Example:
`delete -s 3`

Expected output:
Supplier at index 3 is deleted, assuming it existed initially. Otherwise, an error message will be shown.

#### Here's how it would look like in the app:
TO UPDATE IMAGE AFTER FINAL UPDATE TO APPLICATION!!!
![delete command](images/deleteSupplierCommand.png)

### Mark a supplier with a status : `mark -s`

The `mark` command is used to mark a supplier as either **active** or **inactive**
in VendorVault. This helps you keep track of which suppliers are currently active for deliveries and which are not.

Format: `mark -s SUPPLIER_INDEX STATUS`
- `SUPPLIER_INDEX`: Must be a number greater than 0 and must not be blank.
- `STATUS`: Must be one of the following: `active`, `inactive` and must not be blank.

#### Example
To mark the supplier at index 3 as active:

    mark -s 3 active

A success message will be displayed if the supplier is successfully marked as active.

#### Here's how it would look like in the app:
![mark command](images/markSupplierCommand.png)

### Find a supplier: `find -s`

The `find -s` command is used to find a supplier in VendorVault. 
This helps you find suppliers based on keyword search.

Format: `find -s n/NAME com/COMPANY pro/PRODUCT`

Parameters:

- `n/NAME`: Must be alphanumeric, and must not be blank.
- `com/COMPANY`: Must be alphanumeric, and must not be blank.
- `pro/PRODUCT`: Must be alphanumeric, can include spaces but must not start with a space, and must be between 1 and 50 (inclusive) characters long.

<box type="warning" seamless>

**Warnings**:
- At least one non-empty parameter must be given
- No duplicate parameter can be used
- Find result(s) will contain/satisfy all the given parameters
- Find feature is case-insensitive
</box>


#### Example
To find the supplier whose name contains "link" and company contains "NU":

    find -s n/link com/NU


#### Here's how it would look like in the app:
![find command](images/findSupplierCommand.png)

### Sort suppliers: `sort -s`

The `sort -s` command is used to sort suppliers in VendorVault.
This helps you to view the suppliers in a different order (ascending or descending), based on the supplier name.

Format: `sort -s so/SORT_ORDER sb/SORT_BY`
- `SORT_ORDER`: Must be either 'a' for ascending or 'd' for descending.
- `SORT_BY`: Must be 'n' for name. (Current version of VendorVault only supports sorting by name)

**Warnings**:
- A spacing between `add` and `-s` is compulsory
- All prefixes and parameters must be given
- No duplicate prefix can be used
- Parameters used are **case-sensitive**

#### Example
To sort suppliers by name in descending order:

    sort -s so/d sb/n

A success message will be displayed if the suppliers are successfully sorted.
#### Here's how it would look like in the app:
![sort command](images/sortSupplierCommand.png)
---

## <ins> Delivery Commands </ins>

### Adding a delivery: `add -d`

Adds a delivery to the address book.

Format: `add -d on/DELIVERY_DATE_TIME s/SUPPLIER_INDEX pro/PRODUCT q/QUANTITY kg/g/L/mL/units c/COST`

Parameters:

- `on/DELIVERY_DATE_TIME`: Must be in dd-MM-yyyy hh:mm format, and must not be blank.
- `s/SUPPLIER_INDEX`: Must be a number greater than 0, and must not be blank.
- `pro/PRODUCT`: Must be alphanumeric, can include spaces but must not start with a space, and must be between 1 and 50 (inclusive) characters long.
- `q/QUANTITY`: Must be a number greater than 0 followed by a space, and unit and must not be blank.
- `c/COST`: Must be a number greater than 0 with up to 2 decimal places allowed, and must not be blank.
<box type="tip" seamless>

**Tip:** Day and month of date must be in double digits!
</box>

<box type="warning" seamless>

**Warnings**:
- A spacing between `add` and `-d` is compulsory
- A delivery is considered duplicate if they have the same DATE, TIME, SUPPLIER, PRODUCT and COST
- Duplicate delivery will not be added again
- No duplicate prefix can be used
- Units for QUANTITY is case-sensitive
</box>

#### Example

    add -d on/18-06-2024 17:00 s/1 pro/bread q/500 g c/5.50

#### Here's how it would look like in the app:
![add delivery command](images/addDeliveryCommand.png)

### Listing all deliveries: `list -d`

Shows a list of all supplier in the VendorVault. (The supplier list will not be affected)

Format: `list -d`


<box type="warning" seamless>

**Warnings**:
- No other parameters should be given for this command.
- At least one space between list and -a
</box>

### Marking a delivery : `mark -d`

Marks the specified delivery from the address book with the specified `STATUS`.

Format: `mark -d INDEX STATUS`

Parameters:

- `INDEX`: Must be a number greater than 0 and must not be blank.
- `STATUS`: Must be one of the following: `PENDING`, `DELIVERED`, `CANCELLED` and must not be blank.
<box type="tip" seamless>

**Warnings**:
- A spacing between `mark` and `-d` is compulsory
- Both parameters must be given
- Parameters used are case-insensitive
</box>


Examples:
* `list` followed by `mark -d 2 pending` marks the 2nd delivery in the address book with a pending status.
* `find -d pro/ bread` followed by `mark -d 1 cancelled` marks the 1st delivery in the results of the `find` command with a cancelled status.

#### Here's how it would look like in the app:
![mark delivery command](images/markDeliveryCommand.png)

### Deleting a delivery : `delete -d`

Deletes the specified delivery from the address book.

Format: `delete -d INDEX`

<box type="details" seamless>

Parameters:
- `INDEX`: Must be a number greater than 0 and must not be blank.

</box>

<box type="warning" seamless>

**Warnings**:
- A spacing between `delete` and `-d` is compulsory
- Spacing between `-d` and `INDEX` is not compulsory
- Only one delivery at the specified `INDEX` can be deleted at a time
  - `delete -d 1 3 5` is not allowed

</box>

Examples:
* `list` followed by `delete -d 2` deletes the 2nd delivery in the address book.
* `find -d pro/ bread` followed by `delete -d 1` deletes the 1st delivery in the results of the `find` command.

#### Here's how it would look like in the app:
![delete delivery command](images/deleteDeliveryCommand.png)

### Find a delivery: `find -d`

Find deliveries based on attributes of the delivery, like the delivery date and time, status, supplier and product.

Format: `find -d on/DELIVERY_DATE_TIME stat/STATUS s/SUPPLIER_INDEX pro/PRODUCT`

<box type="details" seamless>

Parameters:

- `on/DELIVERY_DATE_TIME`: `DELIVERY_DATE_TIME` must be in dd-mm-yyyy hh:mm format and must not be blank.
- `stat/STATUS`: `STATUS` must be one of the following: PENDING, DELIVERED, CANCELLED and must not be blank.
- `s/SUPPLIER_INDEX`: `SUPPLIER_INDEX` must be a number greater than 0 and must not be blank.
- `pro/PRODUCT`: `PRODUCT` must be alphanumeric, only contain between 1 and 50 (inclusive) characters, and spaces are also allowed.
    - One space is counted as one character.
    - `PRODUCT` cannot be made up of only spaces.

</box>

<box type="warning" seamless>

**Warnings**:
- A spacing between `find` and `-d` is compulsory
- At least one parameter must be given
- A spacing between `-d` and the first parameter is compulsory
- When using more than one parameter, a spacing between parameters is compulsory
- `STATUS` and `PRODUCT` are not case-sensitive
- No duplicate parameter can be used
  - `find -d pro/milk pro/bread` is not allowed
- Find result(s) will contain/satisfy all the given parameters

</box>


#### Example
To find deliveries of products that contains "milk" on "28-06-2025 17:00" :

    find -d on/ 28-06-2025 17:00 pro/ milk

#### Here's how it would look like in the app:
![find command](images/findDeliveryCommand.png)

### Sort deliveries: `sort -d`

The `sort -d` command is used to sort deliveries in VendorVault based on the delivery cost, date or status.
This helps you to view the deliveries in a different order (ascending or descending).

Format: `sort -d so/SORT_ORDER sb/SORT_BY`

<box type="details" seamless>

Parameters:

- `so/SORT_ORDER`: `SORT_ORDER` must be either `a` for ascending or `d` for descending, and must not be blank.
- `sb/SORT_BY`: `SORT_BY` must be either 'c' for cost, `d` for date or `s` for status, and must not be blank.

</box>

<box type="warning" seamless>

**Warnings**:
- A spacing between `sort` and `-d` is compulsory
- A spacing between `-d` and `so/SORT_ORDER` is compulsory
- A spacing between `so/SORT_ORDER` and `sb/SORT_BY` is compulsory 
- All parameters must be given
- No duplicate parameter can be used
  - `sort -d so/a sb/c sb/d` is not allowed
- `SORT_ORDER` and `SORT_BY` are **case-sensitive**
- Using `sort -d` command will sort and display all deliveries in VendorVault even if you have previously used the `find -d` command

</box>

<box type="tip" seamless>

**Tip:** Sorting deliveries by status in ascending order will display deliveries in the following order: `CANCELLED`, `DELIVERED`, `PENDING`

</box>

#### Example

To sort deliveries by cost in ascending order:

    sort -d so/a sb/c

#### Here's how it would look like in the app:
![sort command](images/sortDeliveriesCommand.png)

### Upcoming deliveries: `upcoming`

The `upcoming` command is used to view pending deliveries in VendorVault.
You can choose to view all pending deliveries within a specified date range or
before or after a given date.

Format: `upcoming aft/START_DATE bef/END_DATE`

Parameters:

- `aft/START_DATE`: Must be in dd-mm-yyyy hh:mm format.
- `bef/END_DATE`: Must be in dd-mm-yyyy hh:mm format.
<box type="tip" seamless>
**Tip:** You can provide both START_DATE and END_DATE!

**Warnings**:
- A spacing between `upcoming` and the first parameter is compulsory
- At least one parameter must be provided
- No duplicate prefix can be used
- The prefixes `aft/` and `bef/` are **case-sensitive**
</box>

#### Example

To view pending deliveries between two dates:

    upcoming aft/19-12-2022 08:00 bef/18-06-2023 17:00

#### Here's how it would look like in the app:
![upcoming command](images/upcomingCommand.png)
---
### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

VendorVault data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

VendorVault automatically saves your data as a JSON file `[JAR file location]/data/vendorvault.json`. Advanced users are welcome to update data directly by editing that data file.
<box type="warning" seamless>

**Caution:**
- **Backup before editing!** If the file is not edited correctly, VendorVault may not be able to read it which will cause all your data to be erased, and the app will start with an empty data file the next time you open it. <br>
- Furthermore, certain edits can cause VendorVault to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

---

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Glossary

* **Supplier Contact**: A record containing information about a supplier, including name, company, contact number, email, associated products and tags.
* **Java**: 

NEED UPDATE TERMS AT THE END!! CAN LMK IF YOU HAVE ANY IN THE PR OR SMTH


[Back to Top](#vendor-vault-user-guide)
