# DATAO.IO  -  SLAM

Slam is a command line application focused on bootstrapping data science projects, as well as running them from end to end.

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

