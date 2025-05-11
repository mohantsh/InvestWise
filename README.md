# InvestWise - Investment Management System

InvestWise is a console-based investment management system that allows users to track and manage their assets while ensuring compliance with investment regulations.

## Features

- **User Management**
  - User registration and authentication
  - Secure login system
  - User profile management

- **Asset Management**
  - Add, edit, and delete assets
  - Track different types of assets
  - View asset portfolio
  - Asset value tracking

- **Compliance Checking**
  - Automated compliance verification
  - Investment regulation adherence
  - Compliance reporting

- **Bank Integration**
  - Bank account linking
  - Secure payment processing
  - OTP verification

## Project Structure

```
src/
├── Main.java              # Main application entry point
├── model/                 # Data models
│   ├── Asset.java        # Asset entity
│   └── User.java         # User entity
├── storage/              # Data persistence
│   ├── AssetStorage.java # Asset data management
│   └── UserStorage.java  # User data management
└── logic/                # Business logic
    └── ComplianceChecker.java # Compliance verification
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven (for dependency management)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/mohantsh/InvestWise.git
   ```

2. Navigate to the project directory:
   ```bash
   cd InvestWise
   ```

3. Compile the project:
   ```bash
   javac src/*.java src/*/*.java
   ```

4. Run the application:
   ```bash
   java -cp src Main
   ```

## Usage

1. **Sign Up/Login**
   - Create a new account or login with existing credentials
   - Follow the on-screen prompts for registration/login

2. **Asset Management**
   - View your current assets
   - Add new assets with type and value
   - Edit or delete existing assets
   - Check compliance status

3. **Bank Integration**
   - Link your bank account
   - Follow the secure verification process
   - Complete OTP verification

## Security Features

- Password protection
- Secure user authentication
- OTP verification for bank linking
- Data encryption for sensitive information

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

Mohantsh - mohantsh@github.com

Project Link: [https://github.com/mohantsh/InvestWise]
