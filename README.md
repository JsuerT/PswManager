============================================================
           PASSWORD MANAGEMENT SYSTEM (JAVA)
              Setup Guide for Android/Termux
============================================================

Introductions on Android via Termux

1. LOCAL SETUP (Directly on Android via Termux)
-----------------------------------------------
Use this if you want to run the application on your phone

Prerequisites:
  $ pkg update && pkg upgrade
  $ pkg install git openjdk-21

Installation via git:
  $ git clone https://github.com/JsuerT/PswManager
  $ cd PswManager
  $ javac Start.java
  $ java Start


2. REMOTE ACCESS (Desktop Control via SSH over ADB)
---------------------------------------------------
Use this to control Termux from your PC/Laptop via USB.


Requirements: 
  application: Termux (mobile)
  Enable USB debugging (mobile)

  preciding prompts (Termux): 
    "pkg update && pkg upgrade"
    "pkg install openjdk-21" 
    "pkg install openssh"
  Installed java, openssh on Computer/Laptop

    
Connecting: 
   android: "passwd" and enter a new password eg. "1234A"  
   android: "sshd"

   on dekstop-terminal (i use nixos): "adb devices" 
   (accept RSA PopUp on mobile)
   on desktop-terminal: "adb forward tcp:8022 tcp:8022"
   on desktop-terminal: "ssh localhost -p 8022"


Exit: 
   on desktop-terminal: "exit"
   on desktop-terminal: "adb forward --remove tcp:8022"
   on desktop-terminal (optional): "pkill sshd"
 
