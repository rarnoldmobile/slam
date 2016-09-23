# DATAO.IO  -  SLAM

Slam is a command line application focused on bootstrapping data science projects, as well as running them from end to end.

[TOC]

## Installation

On OSX: 

```sh

brew install slam
```

On Windows: 

1.  Download the latest release (0.0.1)
2.  Unzip to a folder (pick a folder, any folder)
3.  Add to your path


## Initializing A New Project
```sh

slam --init
```

This will create a new project structure with all default settings

*   Data will reside in the "data" folder
*   Temporary project files will reside in the "temp" folder
*   Scripts, followed by their language specification will be in the scripts folder
    -   i.e. scripts/python, or scripts/r
*   Final project output will be in the "out" folder


## Cleaning a Project

Cleaning will get rid of all output, and temporary artifacts

```sh

slam --clean 
```

## Validating a Project file

Validating the config.slam file being used - can be performed with

```sh

slam --validate
```

This will ensure your config file is well formed, and meets all standards


## Running the Data Pipeline

Running the data pipeline, can be performed by:

```sh

slam --run <arguments>
```

Note however, in order for the pipeline to you run - you *must* have a config.slam file created.  This will be where you declare all tasks.

For more information on the config.slam format - see the section: "Run Configuration".


## Run Configuration

The run configuration is the most important piece of the slam application.

Looking at the underlying structure of the config.slam file, you'll see there are three extemely important sections.

### Tasks

Tasks define the very basic building blocks of what is allowed in your application.  These define:

* What language will be used
* Arguments that will be passed to that system
* What the script is that encapsulates that task

The task has 4 different components that have to be considered.

1.  The id of the task - this has to be unique for every task
2.  The language the task will be using. This *has* to correspond to a language specified in the top "languages" tag
3.  The name of the script we will be invoking.  This is a non case sensitive filename that will be located in the script folders
4.  The arguments that will be passed into the task

Most of this may be straight forward...except for the arguments.  Since we are piping command line programs - we must pass in *everything* that the program needs by way of command arguments.

This will let us define a stream of actions we are performing, and leverage multiple techniques from different technologies.

We will recap what is occuring in that argument string later on...

### Steps

Once we know the tasks that will be used, we then need to define the sequence that we need to run these tasks.  Assuming your pipeline resembles:

*  Pull all necessary data
*  Clean out dataset in python
*  Then write the dataset to a file system somewhere else..

We would first create a task for each, then put each step into this array.

Each step requires only two things:

*  The sequence - a numeric value - that the task should run
*  The id of the te task you wish to run..Since we know the task id already - we simply reuse the id that you assigned previously at the task level


### Variable String

We can't assume that our environment has every variable it will require when running each script.  There could be differences between a production run, vs qa, vs dev.

Our variable string will let us specify a variable list we will be using.

Assuming we use three variables, they appear somewhere within the task configuration.  Our variable names will be:

*  fileName
*  environmentName
*  userName

When we invoke `slam run` - we will pass these values in.  For example - with the above variables:

```sh

slam --run testfile.xlsx prod rob
```

We declare this "variableString" tag as:  ":fileName :environmentName :userName".  Slam will then take each of those values - and put those into the argument string as it steps through each task.

So when we encounter the "runScript1" task - we will use the value of "fileName" as an argument to that string.


