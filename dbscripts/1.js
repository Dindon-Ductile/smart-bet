db.userCombinedBets.find({}).forEach(function (doc) {
        doc.entries.forEach(function (entry) {
                entry.active = true;
            }
        );
        db.userCombinedBets.save(doc);
    }
);

db.userCombinedBets.find({_id: BinData(3,"VUDE0rJO3dDVI3U/mnNLlQ==")}).forEach(function (doc) {
        doc.entries.forEach(function (entry) {
                entry.active = true;
            }
        );
        db.userCombinedBets.save(doc);
    }
);


