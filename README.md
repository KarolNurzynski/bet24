# bet24

AIM
App created a the end of my Coderslab-Amelco Java course. Build in 1 week, after intensive 3 month Java bootcamp.

APP CONTENT
There are few main areas in the app:
1) FEED PROVIDER - where footbal data (like matches and live goals) is produced (you have a frontend panel for producing matches and match live results - for testing)
2) FEED HANDLER - where we reveice feed 'events' and react to them
3) ODDS RECALCULATING - for each game there are odds calculated based on 'team strength' and taking into account current match result and time to the ne of the game; if result changes odds are recalculated
4) BET RESULTING - when hame is over, bets are being resulted (people that placed bets either win or lose and money is transfered to their accounts)
5) USER REGISTRATION & LOGIN PANEL / ADDING BALANCE TO USER ACCOUNT

TECHNOLGIES
- Java 8
- Sring 5 (core, mvc, data, security)
- Hibernate 5
- JMS (ActiveMQ broker) - for event processing (events = goals, end of match)
- multithreading - for load balancing (threads are reading for JMS queues - we can set a number of reading threads deending on the process we want to improve)
