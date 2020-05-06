package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"os/signal"
	"syscall"
	"time"
)

const version = "v1.1.0"
const logFile = "time.log"

func main() {
	clearScreen()
	showTitle()

	for {
		switch showMenu() {
		case 1:
			startTime(25, 5, false)
		case 9:
			showLogs()
		case 0:
			clearScreen()
			os.Exit(0)
		default:
			os.Exit(-1)
		}
	}
}

func clearScreen() {
	cmd := exec.Command("clear")
	cmd.Stdout = os.Stdout
	cmd.Run()
}

func showTitle() {
	fmt.Println("Pomodoro - version: ", version)
}

func showMenu() int {
	fmt.Println("")
	fmt.Println("[1] Star time 25:00 minutes with 5:00 minutes of pause")
	fmt.Println("[9] Show activities Logs")
	fmt.Println("[0] Exit")

	var choice int
	fmt.Scan(&choice)

	return choice
}

func startTime(pomoTime int, pauseTime int, exit bool) {
	minutes := pomoTime
	seconds := 0

	// code block that captures CTRL+C
	c := make(chan os.Signal)
	signal.Notify(c, os.Interrupt, syscall.SIGTERM)
	go func() {
		<-c
		fmt.Println("Stopping forced Pomodoro")
		registerLogs("Stop Pomodoro --force")
		os.Exit(0)
	}()

	registerLogs("Start Pomodoro")

	for {
		clearScreen()
		fmt.Println(minutes, ":", seconds)
		fmt.Println("Press CTRL+C to Exit")

		if minutes == 0 && seconds == 0 {
			if exit {
				break
			} else {
				registerLogs("Stop Pomodoro")
				minutes = pauseTime
				seconds = 0
				exit = true
				registerLogs("Start Break")
			}
		}

		if seconds == 0 {
			minutes--
			seconds = 59
		} else {
			seconds--
		}

		time.Sleep(1 * time.Second)
	}

	registerLogs("Stop Break")
}

// Function to show logs registered by pomodoro activity
func showLogs() {
	clearScreen()
	fmt.Println("Show the last logs...")
	fmt.Println("")

	file, err := ioutil.ReadFile(logFile)

	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println(string(file))
	}
}

func registerLogs(lineRegister string) {
	file, err := os.OpenFile(logFile, os.O_RDWR|os.O_APPEND|os.O_CREATE, 0755)

	if err != nil {
		fmt.Println("Error:", err)
	} else {
		file.WriteString(time.Now().Format("2006/01/02 03:04:05 PM") + " | " + lineRegister + "\n")
		file.Close()
	}
}
