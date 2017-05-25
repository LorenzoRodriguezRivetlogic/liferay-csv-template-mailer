create index IX_E49DD145 on RL_Template (groupId);
create index IX_E68123B9 on RL_Template (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EB41447B on RL_Template (uuid_[$COLUMN_LENGTH:75$], groupId);