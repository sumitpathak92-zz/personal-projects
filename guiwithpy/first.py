#!/usr/bin/python
import tkMessageBox
import Tkinter

top=Tkinter.Tk()

def helloBack():
    tkMessageBox.showinfo("Hello Python", "Hello World")

B = Tkinter.Button(top, text='Hello', command = helloBack)
B.pack()
top.mainloop()
