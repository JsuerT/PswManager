PASSWORDMANAGEMENTSYSTEM

Introductions on Android via Termux

1. Installation via Git (Run PswManager)
   Requirement:
      "pkg install git"
      "pkg install openjdk-21"
   Prompt:
      "git clone https://github.com/JsuerT/PswManager"
      "cd PswManager"
      "javac Start.java"
      "java Start"


///////////////////////////////////////////////////////


2. Remote Access & Control via Desktop (SSH over ADB)
Requirements: 
  application: Termux (mobile)
  Enable USB debugging (mobile)

  preciding prompts (Termux): 
    "pkg update && pkg upgrade"
    "pkg install openjdk-21" 
    "pkg install openssh"
  Installed java, openssh on Laptop

    
Connecting: 
   android: "passwd" and enter a new password  
   android: "sshd"

   on dekstop-terminal (i use nixos): "adb devices" 
   (accept RSA PopUp on mobile)
   on desktop-terminal: "adb forward tcp:8022 tcp:8022"
   on desktop-terminal: "ssh localhost -p 8022"
    
  
