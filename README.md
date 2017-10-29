# `seed-jee` — Seed for JEE Systelab projects

This project is an application skeleton for a typical [JEE] backend application. You can use it
to quickly bootstrap your JEE projects and dev environment.

The seed contains a sample Patient Management application and is preconfigured to install the Angular
framework and a bunch of development and testing tools for instant development gratification.

The seed app doesn't do much, just shows how to wire two controllers and views together.

## Getting Started

To get you started you can simply clone the `seed-jee` repository and install the dependencies:

### Prerequisites

You need git to clone the `seed-jee` repository. You can get git from [here][git].

You will need [Java Development Kit (JDK)][jdk] and Maven. You can get Maven from [here][maven].

### Clone `seed-jee`

Clone the `seed-jee` repository using git:

```
git clone https://github.com/systelab/seed-jee.git
cd seed-jee
```

If you just want to start a new project without the `seed-jee` commit history then you can do:

```
git clone --depth=1 https://github.com/systelab/seed-jee.git <your-project-name>
```

The `depth=1` tells git to only pull down one commit worth of historical data.

### Install Dependencies

In order to install the dependencies you must run:

```
mvn install
```

[git]: https://git-scm.com/
[jasmine]: https://jasmine.github.io/
[jdk-download]: http://www.oracle.com/technetwork/java/javase/downloads
