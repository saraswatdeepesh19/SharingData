title <size:20><color:#1a73e8>API Token Request & Approval Workflow</color></size>

// Set global styles for a modern look
participantstyle #f8f9fa #1a73e8;2
notestyle #fff9c4 #fbc02d;1,<size:11>
dividerstyle #e8f0fe #1a73e8;2,**
activecolor #e8f0fe

// Define Participants with Icons
actor "++User++" as User #e3f2fd #1565c0
participant "++Swagger UI++" as Swagger #bbdefb #1976d2
database "++System DB++" as DB #cfd8dc #455a64
actor "++Manager++" as Manager #e8f5e9 #2e7d32
actor "++Lead++ " as Lead #f3e5f5 #7b1fa2
participant "++App APIs++" as API #fff3e0 #ef6c00

autonumber
User->Swagger: Click [GET TOKEN]
activate Swagger

== 15-Min Cooldown Logic ==
Swagger->DB: Check Cooldown Status
activate DB
DB-->>Swagger: Status Response
deactivate DB

alt#lightpink <color:#d32f2f>**COOLDOWN ACTIVE**</color>
    Swagger-->>User: <color:red>Request  already sent to Manager wait for 15 min  Can trigger next request only after 15 min. (Try again in 15 mins)</color>
else <color:#388e3c>**COOLDOWN EXPIRED**</color>
    Swagger->DB: Set 15-min Disable Flag
    Swagger->DB: Get Daily Request Count
    activate DB
    DB-->>Swagger: Count Value
    deactivate DB

    == Approver Routing ==
    alt#e8f5e9 Count < 2
        Swagger-[#green]>Manager: Send Email to Manager
        activate Manager
        note over Manager:Click email link
        Manager->Swagger: Browser Auth
        Swagger-->>Manager: Display Token
        Manager-->>User: <color:#2e7d32>Transfer via Secure Way</color>
        deactivate Manager
    else Count >= 2
        Swagger-[#purple]>Lead: Send Email to Lead
        activate Lead
        note over Lead:Click email link
        Lead->Swagger: Browser Auth
        Swagger-->>Lead: Display Token
        Lead-->>User: <color:#7b1fa2>Transfer via Secure Way</color>
        deactivate Lead
    end

    Swagger->DB: Refresh Daily Counter
    
    User->Swagger: Authorize (Paste Token)
    User->API: Access APIs
    activate API
    API-->>User: Success 200 OK
    deactivate API
end

deactivate Swagger
