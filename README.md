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


/////////////////////////////////////////////////////////////////////


2. Remote Access & Control via Desktop (SSH over ADB)
Requirements: 
  application: Termux 
  preciding prompts (Termux): 
    "pkg update && pkg upgrade"
    "pkg install openjdk-21" 
    "pkg install openssh"
  Installed java, openssh on Laptop, ...

    
Enable USB debugging (mobile) 
  


Installation: 
  (android via Termux) 
    "passwd" and enter a new password  

  (on terminal (i use nixos)):
    prompt "adb devices"
  
