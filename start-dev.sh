#!/bin/bash
export $(cat .env | xargs)
./gradlew bootRun
