package main

import (
	"fmt"
	"os"
	"os/exec"
	"time"
)

const version = "v1.0.0"

func main() {
	clearScreen()
	showTitle()

	for {
		switch showMenu() {
		case 1:
			startTime(25, 5, false)
		case 9:
			fmt.Println("Not implemented")
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

	for {
		clearScreen()
		fmt.Println(minutes, ":", seconds)
		fmt.Println("Press CTRL+C to Exit")

		if minutes == 0 && seconds == 0 {
			if exit {
				break
			} else {
				minutes = pauseTime
				seconds = 0
				exit = true
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
}
