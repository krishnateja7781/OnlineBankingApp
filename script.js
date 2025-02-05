let accounts = {};

function createAccount() {
  const name = prompt("Enter account holder's name:");
  const accountId = prompt("Set an account ID:");
  const initialDeposit = parseFloat(prompt("Enter initial deposit:"));

  if (name && accountId && !accounts[accountId] && initialDeposit >= 0) {
    accounts[accountId] = {
      name: name,
      balance: initialDeposit,
      history: [`Account created with $${initialDeposit}`],
    };
    alert(`Account created successfully for ${name}`);
  } else {
    alert("Invalid details or account ID already exists.");
  }
}

function deleteAccount() {
  const accountId = prompt("Enter account ID to delete:");
  if (accounts[accountId]) {
    delete accounts[accountId];
    alert("Account deleted successfully.");
  } else {
    alert("Account not found.");
  }
}

function deposit() {
  const accountId = prompt("Enter account ID:");
  const amount = parseFloat(prompt("Enter deposit amount:"));

  if (accounts[accountId] && amount > 0) {
    accounts[accountId].balance += amount;
    accounts[accountId].history.push(`Deposited $${amount}`);
    alert(`Deposited $${amount}. New balance: $${accounts[accountId].balance}`);
  } else {
    alert("Invalid account ID or amount.");
  }
}

function withdraw() {
  const accountId = prompt("Enter account ID:");
  const amount = parseFloat(prompt("Enter withdrawal amount:"));

  if (accounts[accountId] && amount > 0 && amount <= accounts[accountId].balance) {
    accounts[accountId].balance -= amount;
    accounts[accountId].history.push(`Withdrew $${amount}`);
    alert(`Withdrew $${amount}. New balance: $${accounts[accountId].balance}`);
  } else {
    alert("Invalid account ID, amount, or insufficient balance.");
  }
}

function checkBalance() {
  const accountId = prompt("Enter account ID:");
  if (accounts[accountId]) {
    alert(`Account Balance: $${accounts[accountId].balance}`);
  } else {
    alert("Account not found.");
  }
}

function viewTransactionHistory() {
  const accountId = prompt("Enter account ID:");
  if (accounts[accountId]) {
    alert(`Transaction History for ${accounts[accountId].name}:\n` + accounts[accountId].history.join("\n"));
  } else {
    alert("Account not found.");
  }
}

function viewAllAccounts() {
  const confirmation = confirm("Do you want to view all account details?");
  if (confirmation) {
    const accountDetails = document.getElementById("account-details");
    const accountList = document.getElementById("accounts");

    accountList.innerHTML = "";

    for (const accountId in accounts) {
      const account = accounts[accountId];
      const listItem = document.createElement("li");
      listItem.textContent = `Account Holder: ${account.name}, Balance: $${account.balance}`;
      accountList.appendChild(listItem);
    }

    accountDetails.style.display = "block";
  } else {
    alert("Access denied.");
  }
}

function exitService() {
  alert("Thank you for using the Online Banking Application!");
}
