#!/bin/bash
sudo -u postgres -H -- psql -d slouke -c "TRUNCATE TABLE messages"
