package com.chatapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.User;
import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.infracstructure.services.MD5Hasher;
import com.chatapplication.infrastructure.data.InMemoryDataStorage;
import com.chatapplication.infrastructure.data.InMemoryGroupConversationStorage;
import com.chatapplication.infrastructure.data.InMemoryIndividualConversationStorage;
import com.chatapplication.infrastructure.data.InMemoryPrivateGroupStorage;
import com.chatapplication.infrastructure.data.InMemoryPublicGroupStorage;
import com.chatapplication.usecases.user.CreatePrivateGroup;
import com.chatapplication.usecases.user.CreatePublicGroup;
import com.chatapplication.usecases.user.DeleteMessageGroupByOwner;
import com.chatapplication.usecases.user.DeleteMessagePrivateGroupByAdmin;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsGroup;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsIndividual;
import com.chatapplication.usecases.user.FindUsersByGivenName;
import com.chatapplication.usecases.user.GetAllAttendedGroups;
import com.chatapplication.usecases.user.GetAllGroupConversations;
import com.chatapplication.usecases.user.GetAllIndividualConversations;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMGroup;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMIndividual;
import com.chatapplication.usecases.user.GetLatestReadMessageGroup;
import com.chatapplication.usecases.user.JoinPrivateGroup;
import com.chatapplication.usecases.user.JoinPublicGroupByCode;
import com.chatapplication.usecases.user.JoinPublicGroupByInvitation;
import com.chatapplication.usecases.user.LeavePrivateGroupAdmin;
import com.chatapplication.usecases.user.LeavePrivateGroupMember;
import com.chatapplication.usecases.user.LeavePublicGroup;
import com.chatapplication.usecases.user.LoginUseCase;
import com.chatapplication.usecases.user.RemoveMemberOfPrivateGroupByAdmin;
import com.chatapplication.usecases.user.RemoveMemberOfPublicGroupByAdmin;
import com.chatapplication.usecases.user.UpdateReadMessageGroup;
import com.chatapplication.usecases.user.UpdateReadMessageIndividual;
import com.chatapplication.usecases.user.LoginUseCase.InputLoginUseCase;
import com.chatapplication.usecases.user.LoginUseCase.LoginResult;
import com.chatapplication.usecases.user.LoginUseCase.OutputLoginUseCase;
import com.chatapplication.usecases.user.RemoveMemberOfPrivateGroupByAdmin.InputRemoveMemberOfPrivateGroupByAdmin;
import com.chatapplication.usecases.user.RemoveMemberOfPrivateGroupByAdmin.OutputRemoveMemberOfPrivateGroupByAdmin;
import com.chatapplication.usecases.user.RemoveMemberOfPrivateGroupByAdmin.RemoveMemberOfPrivateGroupByAdminResult;
import com.chatapplication.usecases.user.RemoveMemberOfPublicGroupByAdmin.InputRemoveMemberOfPublicGroupByAdmin;
import com.chatapplication.usecases.user.RemoveMemberOfPublicGroupByAdmin.OutputRemoveMemberOfPublicGroupByAdmin;
import com.chatapplication.usecases.user.RemoveMemberOfPublicGroupByAdmin.RemoveMemberOfPublicGroupByAdminResult;
import com.chatapplication.usecases.user.UpdateReadMessageGroup.InputUpdateReadMessageGroup;
import com.chatapplication.usecases.user.UpdateReadMessageGroup.OutputUpdateReadMessageGroup;
import com.chatapplication.usecases.user.UpdateReadMessageGroup.UpdateReadMessageGroupResult;
import com.chatapplication.usecases.user.UpdateReadMessageIndividual.InputUpdateReadMessagesIndividual;
import com.chatapplication.usecases.user.UpdateReadMessageIndividual.OutputUpdateReadMessagesIndividual;
import com.chatapplication.usecases.user.UpdateReadMessageIndividual.UpdateReadMessagesIndividualResult;
import com.chatapplication.usecases.user.UserRegistration;
import com.chatapplication.usecases.user.CreateGroupMessage.CreateGroupMessageResult;
import com.chatapplication.usecases.user.CreateGroupMessage.InputCreateGroupMessage;
import com.chatapplication.usecases.user.CreateGroupMessage.OutputCreateGroupMessage;
import com.chatapplication.usecases.user.CreateIndividualConversation.CreateIndividualConversationResult;
import com.chatapplication.usecases.user.CreateIndividualConversation.InputCreateIndividualConversation;
import com.chatapplication.usecases.user.CreateIndividualConversation.OutputCreateIndividualConversation;
import com.chatapplication.usecases.user.CreateIndividualMessage.CreateIndividualMessageResult;
import com.chatapplication.usecases.user.CreateIndividualMessage.InputCreateIndividualMessage;
import com.chatapplication.usecases.user.CreateIndividualMessage.OutputCreateIndividualMessage;
import com.chatapplication.usecases.user.CreatePrivateGroup.CreatePrivateGroupResult;
import com.chatapplication.usecases.user.CreatePrivateGroup.InputCreatePrivateGroup;
import com.chatapplication.usecases.user.CreatePrivateGroup.OutputCreatePrivateGroup;
import com.chatapplication.usecases.user.CreatePublicGroup.CreatePublicGroupResult;
import com.chatapplication.usecases.user.CreatePublicGroup.InputCreatePublicGroup;
import com.chatapplication.usecases.user.CreatePublicGroup.OutputCreatePublicGroup;
import com.chatapplication.usecases.user.DeleteMessageGroupByOwner.DeleteByOwnerResult;
import com.chatapplication.usecases.user.DeleteMessageGroupByOwner.InputDeleteByOwner;
import com.chatapplication.usecases.user.DeleteMessageGroupByOwner.OutputDeleteByOwner;
import com.chatapplication.usecases.user.DeleteMessagePrivateGroupByAdmin.DeleteMessagePrivateGroupResult;
import com.chatapplication.usecases.user.DeleteMessagePrivateGroupByAdmin.InputDeleteMessagePrivateGroup;
import com.chatapplication.usecases.user.DeleteMessagePrivateGroupByAdmin.OutputDeleteMessagePrivateGroup;
import com.chatapplication.usecases.user.DeleteNLatestMessagesIndividual.InputDeleteMessage;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsGroup.FindMessageGroupResult;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsGroup.InputFindMessageGroup;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsGroup.OutputFindMessageGroup;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsIndividual.FindMessageIndividualResult;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsIndividual.InputFindMessageIndividual;
import com.chatapplication.usecases.user.FindMessagesContainKeywordsIndividual.OutputFindMessageIndividual;
import com.chatapplication.usecases.user.FindUsersByGivenName.FindUsersByGivenNameResult;
import com.chatapplication.usecases.user.FindUsersByGivenName.InputFindUsersByGivenName;
import com.chatapplication.usecases.user.FindUsersByGivenName.OutputFindUsersByGivenName;
import com.chatapplication.usecases.user.GetAllAttendedGroups.GetAllAttendedGroupsResult;
import com.chatapplication.usecases.user.GetAllAttendedGroups.InputGetAllAttendedGroups;
import com.chatapplication.usecases.user.GetAllAttendedGroups.OutputGetAllAttendedGroups;
import com.chatapplication.usecases.user.GetAllGroupConversations.GetAllGroupConversationsResult;
import com.chatapplication.usecases.user.GetAllGroupConversations.InputGetAllGroupConversations;
import com.chatapplication.usecases.user.GetAllGroupConversations.OutputGetAllGroupConversations;
import com.chatapplication.usecases.user.GetAllIndividualConversations.GetAllIndividualConversationsResult;
import com.chatapplication.usecases.user.GetAllIndividualConversations.InputGetAllIndividualConversations;
import com.chatapplication.usecases.user.GetAllIndividualConversations.OutputGetAllIndividualConversations;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMGroup.GetKLatestMessagesBeforeMGroupResult;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMGroup.InputGetKLatestMessagesBeforeMGroup;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMGroup.OutputGetKLatestMessagesBeforeMGroup;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMIndividual.GetKLatestMessagesBeforeMIndividualResult;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMIndividual.InputGetKLatestMessagesBeforeMIndividual;
import com.chatapplication.usecases.user.GetKLatestMessagesBeforeMIndividual.OutputGetKLatestMessagesBeforeMIndividual;
import com.chatapplication.usecases.user.GetLatestReadMessageGroup.GetLatestReadMessageGroupResult;
import com.chatapplication.usecases.user.GetLatestReadMessageGroup.InputGetLatestReadMessageGroup;
import com.chatapplication.usecases.user.GetLatestReadMessageGroup.OutputGetLatestReadMessageGroup;
import com.chatapplication.usecases.user.JoinPrivateGroup.InputJoinPrivateGroup;
import com.chatapplication.usecases.user.JoinPrivateGroup.JoinPrivateGroupResult;
import com.chatapplication.usecases.user.JoinPrivateGroup.OutputJoinPrivateGroup;
import com.chatapplication.usecases.user.JoinPublicGroupByCode.InputJoinPublicGroupByCode;
import com.chatapplication.usecases.user.JoinPublicGroupByCode.JoinPublicGroupByCodeResult;
import com.chatapplication.usecases.user.JoinPublicGroupByCode.OutputJoinPublicGroupByCode;
import com.chatapplication.usecases.user.JoinPublicGroupByInvitation.InputJoinPublicGroupByInvitation;
import com.chatapplication.usecases.user.JoinPublicGroupByInvitation.JoinPublicGroupByInvitationResult;
import com.chatapplication.usecases.user.JoinPublicGroupByInvitation.OutputJoinPublicGroupByInvitation;
import com.chatapplication.usecases.user.LeavePrivateGroupAdmin.InputLeavePrivateGroupAdmin;
import com.chatapplication.usecases.user.LeavePrivateGroupAdmin.LeavePrivateGroupAdminResult;
import com.chatapplication.usecases.user.LeavePrivateGroupAdmin.OutputLeavePrivateGroupAdmin;
import com.chatapplication.usecases.user.LeavePrivateGroupMember.InputLeavePrivateGroupMember;
import com.chatapplication.usecases.user.LeavePrivateGroupMember.LeavePrivateGroupMemberResult;
import com.chatapplication.usecases.user.LeavePrivateGroupMember.OutputLeavePrivateGroupMember;
import com.chatapplication.usecases.user.LeavePublicGroup.InputLeavePublicGroup;
import com.chatapplication.usecases.user.LeavePublicGroup.LeavePublicGroupResult;
import com.chatapplication.usecases.user.LeavePublicGroup.OutputLeavePublicGroup;
import com.chatapplication.usecases.user.UserRegistration.InputRegistration;
import com.chatapplication.usecases.user.UserRegistration.OutputRegistration;
import com.chatapplication.usecases.user.UserRegistration.RegistrationResult;
import com.chatapplication.usecases.user.CreateGroupMessage;
import com.chatapplication.usecases.user.CreateIndividualConversation;
import com.chatapplication.usecases.user.CreateIndividualMessage;

public class TestingChatApplication {
    InMemoryDataStorage dataStorage;
    MD5Hasher hasher;

    LoginUseCase login;
    UserRegistration userRegistration;

    CreatePrivateGroup createPrivateGroup;
    CreatePublicGroup createPublicGroup;
    InMemoryPublicGroupStorage publicGroupStorage;
    InMemoryPrivateGroupStorage privateGroupStorage;
    InMemoryGroupConversationStorage groupConversationStorage;
    InMemoryIndividualConversationStorage individualConversationStorage;

    JoinPrivateGroup joinPrivateGroup;
    JoinPublicGroupByCode joinPublicGroupByCode;
    JoinPublicGroupByInvitation joinPublicGroupByInvitation;
    LeavePrivateGroupAdmin leavePrivateGroupAdmin;
    LeavePrivateGroupMember leavePrivateGroupMember;
    LeavePublicGroup leavePublicGroup;
    RemoveMemberOfPrivateGroupByAdmin removeMemberOfPrivateGroupByAdmin;
    RemoveMemberOfPublicGroupByAdmin removeMemberOfPublicGroupByAdmin;
    GetAllAttendedGroups getAllAttendedGroups;
    CreateGroupMessage createGroupMessage;
    CreateIndividualConversation createIndividualConversation;
    CreateIndividualMessage createIndividualMessage;

    User usingUser;
    String idPrivateGroup;
    String idPublicGroup;
    String joinCode;
    User userJoin;
    String idIndividualConversation;

    @Before
    public void setUp() {
        userJoin = new User("MinhTuan", "MinhTuan");
        dataStorage = dataStorage.getInstance();
        publicGroupStorage = publicGroupStorage.getInstance();
        privateGroupStorage = privateGroupStorage.getInstance();
        groupConversationStorage = groupConversationStorage.getInstance();
        individualConversationStorage = individualConversationStorage.getInstance();

        hasher = new MD5Hasher();
        login = new LoginUseCase(dataStorage, hasher);

        userRegistration = new UserRegistration(dataStorage, hasher);
        userRegistration.excute(new InputRegistration("Tuan", "Tuan"));

        InputLoginUseCase inputLogin = new InputLoginUseCase("Tuan", "Tuan");
        OutputLoginUseCase outputLogin = login.excute(inputLogin);
        usingUser = outputLogin.getUser();

        createPrivateGroup = new CreatePrivateGroup(privateGroupStorage);
        InputCreatePrivateGroup inputCreatePrivateGroup = new InputCreatePrivateGroup("Private Group General",usingUser);
        OutputCreatePrivateGroup outputJoinPrivateGroup = createPrivateGroup.excute(inputCreatePrivateGroup);
        idPrivateGroup = outputJoinPrivateGroup.getIdGroup();

        createPublicGroup = new CreatePublicGroup(publicGroupStorage);
        InputCreatePublicGroup inputCreatePublicGroup = new InputCreatePublicGroup("Public Group General",usingUser);
        OutputCreatePublicGroup outputCreatePublicGroup = createPublicGroup.excute(inputCreatePublicGroup);
        joinCode = outputCreatePublicGroup.getJoinCode();
        idPublicGroup = outputCreatePublicGroup.getIdGroup();

        createIndividualConversation = new CreateIndividualConversation(individualConversationStorage);
        InputCreateIndividualConversation inputIndividualConversation = 
        new InputCreateIndividualConversation(usingUser.getID(), userJoin.getID());
        OutputCreateIndividualConversation outputCreateIndividualConversation = 
        createIndividualConversation.excute(inputIndividualConversation);
        idIndividualConversation = outputCreateIndividualConversation.getIdConversation();

        
    }

    @Test
    public void login() {
        InputLoginUseCase input = new InputLoginUseCase("Tuan", "Tuan");
        OutputLoginUseCase output = login.excute(input);
        assertEquals(output.getResult(),LoginResult.Successed);
    }

    @Test
    public void userRegistration() {
        InputRegistration input = new InputRegistration("Tuan", "Tuan");
        OutputRegistration output = userRegistration.excute(input);
        assertEquals(output.getResult(),RegistrationResult.Failed);
    }
    
    @Test
    public void createPublicGroup() {
        createPublicGroup = new CreatePublicGroup(publicGroupStorage);
        InputCreatePublicGroup inputCreatePublicGroup = new InputCreatePublicGroup("Public Group",usingUser);
        OutputCreatePublicGroup output = createPublicGroup.excute(inputCreatePublicGroup);
        assertEquals(output.getResult(), CreatePublicGroupResult.Successed);
    }
    
    @Test
    public void createPrivateGroup() {
        createPrivateGroup = new CreatePrivateGroup(privateGroupStorage);
        InputCreatePrivateGroup inputCreatePrivateGroup = new InputCreatePrivateGroup("Private Group",usingUser);
        OutputCreatePrivateGroup output = createPrivateGroup.excute(inputCreatePrivateGroup);
        idPrivateGroup = output.getIdGroup();
        assertEquals(output.getResult(), CreatePrivateGroupResult.Successed);
    }

    @Test
    public void joinPrivateGroup() {
        joinPrivateGroup = new JoinPrivateGroup(privateGroupStorage);
        InputJoinPrivateGroup input = new InputJoinPrivateGroup(userJoin,idPrivateGroup,usingUser.getID());
        OutputJoinPrivateGroup ouput = joinPrivateGroup.excute(input);
        assertEquals(ouput.getResult(), JoinPrivateGroupResult.Successed); 
    }

    @Test
    public void joinCreateGroupByCode() {
        joinPublicGroupByCode = new JoinPublicGroupByCode(publicGroupStorage);
        InputJoinPublicGroupByCode inputJoinPublicGroupByCode = new InputJoinPublicGroupByCode(userJoin, idPublicGroup, joinCode);
        OutputJoinPublicGroupByCode outputJoinPublicGroupByCode = joinPublicGroupByCode.excute(inputJoinPublicGroupByCode);
        assertEquals(outputJoinPublicGroupByCode.getResult(), JoinPublicGroupByCodeResult.Successed);
    }

    @Test 
    public void joinCreateGroupByInvitation() {
        joinPublicGroupByInvitation = new JoinPublicGroupByInvitation(publicGroupStorage);
        InputJoinPublicGroupByInvitation inputJoinPublicGroupByInvitation = 
        new InputJoinPublicGroupByInvitation(usingUser, idPublicGroup, usingUser.getID());
        OutputJoinPublicGroupByInvitation outputJoinPublicGroupByInvitation =
        joinPublicGroupByInvitation.excute(inputJoinPublicGroupByInvitation);
        assertEquals(outputJoinPublicGroupByInvitation.getResult(),JoinPublicGroupByInvitationResult.Successed);
    }

    @Test
    public void leavePrivateGroupAdmin() {
        leavePrivateGroupAdmin = new LeavePrivateGroupAdmin(privateGroupStorage);
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        _privateGroup.setAdmin(userJoin);
        InputLeavePrivateGroupAdmin inputLeavePrivateGroupAdmin = new InputLeavePrivateGroupAdmin(idPrivateGroup,userJoin.getID());
        OutputLeavePrivateGroupAdmin outputLeavePrivateGroupAdmin = leavePrivateGroupAdmin.excute(inputLeavePrivateGroupAdmin);
        
        assertEquals(outputLeavePrivateGroupAdmin.getResult(), LeavePrivateGroupAdminResult.Successed);
    }

    @Test 
    public void leavePrivateGroupMember() {
        leavePrivateGroupMember = new LeavePrivateGroupMember(privateGroupStorage);
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        _privateGroup.addMember(userJoin);
        InputLeavePrivateGroupMember inputLeavePrivateGroupMember = new InputLeavePrivateGroupMember(idPrivateGroup, userJoin.getID());
        OutputLeavePrivateGroupMember outputLeavePrivateGroupMember = leavePrivateGroupMember.excute(inputLeavePrivateGroupMember);
        assertEquals(outputLeavePrivateGroupMember.getResult(), LeavePrivateGroupMemberResult.Successed);
    }

    @Test
    public void leavePublicGroup() {
        leavePublicGroup = new LeavePublicGroup(publicGroupStorage);
        PublicGroup _publicGroup = publicGroupStorage.getPublicGroup().getById(idPublicGroup);
        _publicGroup.addMember(userJoin);
        InputLeavePublicGroup inputLeavePublicGroup = new InputLeavePublicGroup(idPublicGroup, userJoin.getID());
        OutputLeavePublicGroup outputLeavePublicGroup = leavePublicGroup.excute(inputLeavePublicGroup);
        assertEquals(outputLeavePublicGroup.getResult(), LeavePublicGroupResult.Successed);
    }

    @Test
    public void removeMemberOfPrivateGroupByAdmin() {
        removeMemberOfPrivateGroupByAdmin = new RemoveMemberOfPrivateGroupByAdmin(privateGroupStorage);
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        _privateGroup.addMember(userJoin);

        InputRemoveMemberOfPrivateGroupByAdmin inputRemoveMemberOfPrivateGroupByAdmin =
        new InputRemoveMemberOfPrivateGroupByAdmin(idPrivateGroup, userJoin.getID(), usingUser.getID());
        OutputRemoveMemberOfPrivateGroupByAdmin outputRemoveMemberOfPrivateGroupByAdmin = 
        removeMemberOfPrivateGroupByAdmin.excute(inputRemoveMemberOfPrivateGroupByAdmin);
        assertEquals(outputRemoveMemberOfPrivateGroupByAdmin.getResult(), RemoveMemberOfPrivateGroupByAdminResult.Successed);
    }

    @Test
    public void removeMemberOfPublicGroupByAdmin(){
        removeMemberOfPublicGroupByAdmin = new RemoveMemberOfPublicGroupByAdmin(publicGroupStorage);
        PublicGroup _publicGroup = publicGroupStorage.getPublicGroup().getById(idPublicGroup);
        _publicGroup.addMember(userJoin);

        InputRemoveMemberOfPublicGroupByAdmin inputRemoveMemberOfPublicGroupByAdmin = 
        new InputRemoveMemberOfPublicGroupByAdmin(idPublicGroup, userJoin.getID(),usingUser.getID());
        OutputRemoveMemberOfPublicGroupByAdmin outputRemoveMemberOfPublicGroupByAdmin =
        removeMemberOfPublicGroupByAdmin.excute(inputRemoveMemberOfPublicGroupByAdmin);
        assertEquals(outputRemoveMemberOfPublicGroupByAdmin.getResult(), RemoveMemberOfPublicGroupByAdminResult.Successed);
    }

    @Test
    public void getAllAttendedGroups() {
        getAllAttendedGroups = new GetAllAttendedGroups(publicGroupStorage,privateGroupStorage);
        InputGetAllAttendedGroups inputGetAllAttendedGroups = new InputGetAllAttendedGroups(usingUser.getID());
        OutputGetAllAttendedGroups outputGetAllAttendedGroups = getAllAttendedGroups.excute(inputGetAllAttendedGroups);
        assertEquals(outputGetAllAttendedGroups.getGroups().size(), 2);
    }

    @Test
    public void createGroupMessage() {
        PublicGroup _publicGroup = publicGroupStorage.getPublicGroup().getById(idPublicGroup);
        GroupConversation conversation = groupConversationStorage.getConversation().getById(_publicGroup.getIdConversation());

        createGroupMessage = new CreateGroupMessage(groupConversationStorage);
        InputCreateGroupMessage inputCreateGroupMessage = 
        new InputCreateGroupMessage(idPublicGroup, usingUser.getID(), conversation.getID(), "Aaaaaaa");
        OutputCreateGroupMessage outputCreateGroupMessage = createGroupMessage.excute(inputCreateGroupMessage);

        assertEquals(outputCreateGroupMessage.getResult(), CreateGroupMessageResult.Successed);
    }

    @Test
    public void createIndividualConversation() {
        createIndividualConversation = new CreateIndividualConversation(individualConversationStorage);
        InputCreateIndividualConversation inputIndividualConversation = 
        new InputCreateIndividualConversation(usingUser.getID(), userJoin.getID());
        OutputCreateIndividualConversation outputCreateIndividualConversation = 
        createIndividualConversation.excute(inputIndividualConversation);
        assertEquals(outputCreateIndividualConversation.getResult(), CreateIndividualConversationResult.Failed);
    }

    @Test
    public void createIndividualMessage() {
        createIndividualMessage = new CreateIndividualMessage(individualConversationStorage);
        InputCreateIndividualMessage inputIndividualMessage = 
        new InputCreateIndividualMessage(usingUser.getID(), usingUser.getID(), idIndividualConversation, "bbbbbb");
        OutputCreateIndividualMessage outputCreateGroupMessage = createIndividualMessage.excute(inputIndividualMessage);
        assertEquals(outputCreateGroupMessage.getResult(), CreateIndividualMessageResult.Successed);

    }

    @Test
    public void deleteMessageByOwner() {
        PublicGroup _publicGroup = publicGroupStorage.getPublicGroup().getById(idPublicGroup);
        GroupConversation conversation = 
        groupConversationStorage.getConversation().getById(_publicGroup.getIdConversation());
        DeleteMessageGroupByOwner deleteMessageGroupByOwner = 
        new DeleteMessageGroupByOwner(groupConversationStorage, publicGroupStorage);
        createGroupMessage = new CreateGroupMessage(groupConversationStorage);
        InputCreateGroupMessage inputCreateGroupMessage = 
        new InputCreateGroupMessage(idPublicGroup, usingUser.getID(), conversation.getID(), "Aaaaaaa");
        OutputCreateGroupMessage outputCreateGroupMessage = createGroupMessage.excute(inputCreateGroupMessage);

        InputDeleteByOwner inputDeleteByOwner = 
        new InputDeleteByOwner(idPublicGroup, outputCreateGroupMessage.getIdMassage(), usingUser.getID());
        OutputDeleteByOwner outputDeleteByOwner = deleteMessageGroupByOwner.excute(inputDeleteByOwner);
        assertEquals(outputDeleteByOwner.getResult(), DeleteByOwnerResult.Successed);
    }

    @Test 
    public void deleteMessagePrivateByAdmin() {
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        GroupConversation conversation = groupConversationStorage.getConversation().getById(_privateGroup.getIdConversation());
        DeleteMessagePrivateGroupByAdmin deleteMessagePrivateGroupByAdmin = 
        new DeleteMessagePrivateGroupByAdmin(privateGroupStorage, groupConversationStorage);
        createGroupMessage = new CreateGroupMessage(groupConversationStorage);
        InputCreateGroupMessage inputCreateGroupMessage = 
        new InputCreateGroupMessage(idPublicGroup, usingUser.getID(), conversation.getID(), "Aaaaaaa");
        OutputCreateGroupMessage outputCreateGroupMessage = createGroupMessage.excute(inputCreateGroupMessage);

        InputDeleteMessagePrivateGroup inputDeleteMessagePrivateGroup = 
        new InputDeleteMessagePrivateGroup(idPrivateGroup, usingUser.getID(), outputCreateGroupMessage.getIdMassage());
        OutputDeleteMessagePrivateGroup outputDeleteMessagePrivateGroup = 
        deleteMessagePrivateGroupByAdmin.excute(inputDeleteMessagePrivateGroup);
        assertEquals(outputDeleteMessagePrivateGroup.getResult(), DeleteMessagePrivateGroupResult.Successed);
        
    }

    @Test
    public void getAllIndividualConversation() {
        GetAllIndividualConversations getAllIndividualConversations = 
        new GetAllIndividualConversations(individualConversationStorage);
        InputGetAllIndividualConversations inputIndividualConversations = 
        new InputGetAllIndividualConversations(usingUser.getID());
        OutputGetAllIndividualConversations outputGetAllIndividualConversations = 
        getAllIndividualConversations.excute(inputIndividualConversations);
        assertEquals(outputGetAllIndividualConversations.getResult(), GetAllIndividualConversationsResult.Successed);
    }

    @Test
    public void getAllGroupConversation() {
        GetAllGroupConversations getAllGroupConversations = new GetAllGroupConversations(groupConversationStorage);
        InputGetAllGroupConversations inputGetAllGroupConversations = new InputGetAllGroupConversations(usingUser.getID());
        OutputGetAllGroupConversations outputGetAllGroupConversations = getAllGroupConversations.excute(inputGetAllGroupConversations);
        assertEquals(outputGetAllGroupConversations.getResult(), GetAllGroupConversationsResult.Successed);
    }

    @Test 
    public void updateReadMessageGroup() {
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        GroupConversation conversation = groupConversationStorage.getConversation().getById(_privateGroup.getIdConversation());
        createGroupMessage = new CreateGroupMessage(groupConversationStorage);
        InputCreateGroupMessage inputCreateGroupMessage = 
        new InputCreateGroupMessage(idPrivateGroup, usingUser.getID(), conversation.getID(), "Aaaaaaa");
        OutputCreateGroupMessage outputCreateGroupMessage = createGroupMessage.excute(inputCreateGroupMessage);

        UpdateReadMessageGroup updateReadMessageGroup = new UpdateReadMessageGroup(groupConversationStorage);
        InputUpdateReadMessageGroup inputUpdateReadMessageGroup = 
        new InputUpdateReadMessageGroup(conversation.getID(), outputCreateGroupMessage.getIdMassage(), userJoin.getID());
        OutputUpdateReadMessageGroup outputUpdateReadMessageGroup = updateReadMessageGroup.excute(inputUpdateReadMessageGroup);
        assertEquals(outputUpdateReadMessageGroup.getResult(), UpdateReadMessageGroupResult.Successed);
    }

    @Test
    public void updateReadMessageIndividual() {
        createIndividualMessage = new CreateIndividualMessage(individualConversationStorage);
        InputCreateIndividualMessage inputIndividualMessage = 
        new InputCreateIndividualMessage(usingUser.getID(), usingUser.getID(), idIndividualConversation, "bbbbbb");
        OutputCreateIndividualMessage outputCreateGroupMessage = createIndividualMessage.excute(inputIndividualMessage);

        UpdateReadMessageIndividual updateReadMessageIndividual = new UpdateReadMessageIndividual(individualConversationStorage);
        InputUpdateReadMessagesIndividual inputUpdateReadMessagesIndividual = 
        new InputUpdateReadMessagesIndividual(idIndividualConversation, outputCreateGroupMessage.getIdMessage(), userJoin.getID());
        OutputUpdateReadMessagesIndividual outputUpdateReadMessagesIndividual = updateReadMessageIndividual.excute(inputUpdateReadMessagesIndividual);
        assertEquals(outputUpdateReadMessagesIndividual.getResult(), UpdateReadMessagesIndividualResult.Successed);
    }

    @Test
    public void findMessagesContainKeywordsGroup() {
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        GroupConversation conversation = groupConversationStorage.getConversation().getById(_privateGroup.getIdConversation());
        createGroupMessage = new CreateGroupMessage(groupConversationStorage);
        InputCreateGroupMessage inputCreateGroupMessage = 
        new InputCreateGroupMessage(idPrivateGroup, usingUser.getID(), conversation.getID(), "Aaaaaaa");
        OutputCreateGroupMessage outputCreateGroupMessage = createGroupMessage.excute(inputCreateGroupMessage);

        FindMessagesContainKeywordsGroup findMessagesContainKeywordsGroup = 
        new FindMessagesContainKeywordsGroup(groupConversationStorage);
        InputFindMessageGroup inputFindMessageGroup = new InputFindMessageGroup(conversation.getID(), "a");
        OutputFindMessageGroup outputFindMessageGroup = findMessagesContainKeywordsGroup.excute(inputFindMessageGroup);
        assertEquals(outputFindMessageGroup.getResult(), FindMessageGroupResult.Successed);
    }

    @Test
    public void findMessagesContainKeywordsIndividual() {
        createIndividualMessage = new CreateIndividualMessage(individualConversationStorage);
        InputCreateIndividualMessage inputIndividualMessage = 
        new InputCreateIndividualMessage(usingUser.getID(), usingUser.getID(), idIndividualConversation, "bbbbbb");
        OutputCreateIndividualMessage outputCreateGroupMessage = createIndividualMessage.excute(inputIndividualMessage);

        FindMessagesContainKeywordsIndividual findMessagesContainKeywordsIndividual = 
        new FindMessagesContainKeywordsIndividual(individualConversationStorage);
        InputFindMessageIndividual inputFindMessageIndividual = 
        new InputFindMessageIndividual(idIndividualConversation, "b");
        OutputFindMessageIndividual outputFindMessageIndividual = 
        findMessagesContainKeywordsIndividual.excute(inputFindMessageIndividual);
        assertEquals(outputFindMessageIndividual.getResult(), FindMessageIndividualResult.Successed);
    }

    @Test
    public void findUserByGivenName() {
        FindUsersByGivenName findUsersByGivenName = new FindUsersByGivenName(dataStorage);
        InputFindUsersByGivenName inputFindUsersByGivenName = new InputFindUsersByGivenName("Abcxyz");
        OutputFindUsersByGivenName outputFindUsersByGivenName = findUsersByGivenName.excute(inputFindUsersByGivenName);
        assertEquals(outputFindUsersByGivenName.getResult(), FindUsersByGivenNameResult.Failed);
    }

    @Test
    public void getKLatestMessagesBeforeMGroup() {
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        GroupConversation conversation = groupConversationStorage.getConversation().getById(_privateGroup.getIdConversation());
        createGroupMessage = new CreateGroupMessage(groupConversationStorage);
        InputCreateGroupMessage inputCreateGroupMessage = 
        new InputCreateGroupMessage(idPrivateGroup, usingUser.getID(), conversation.getID(), "Aaaaaaa");
        OutputCreateGroupMessage outputCreateGroupMessage = createGroupMessage.excute(inputCreateGroupMessage);

        GetKLatestMessagesBeforeMGroup getKLatestMessagesBeforeMGroup = new GetKLatestMessagesBeforeMGroup(groupConversationStorage);
        InputGetKLatestMessagesBeforeMGroup inputGetKLatestMessagesBeforeMGroup = 
        new InputGetKLatestMessagesBeforeMGroup(conversation.getID(), 1, 0);
        OutputGetKLatestMessagesBeforeMGroup outputGetKLatestMessagesBeforeMGroup = 
        getKLatestMessagesBeforeMGroup.excute(inputGetKLatestMessagesBeforeMGroup);
        assertEquals(outputGetKLatestMessagesBeforeMGroup.getResult(), GetKLatestMessagesBeforeMGroupResult.Successed);
    }

    @Test 
    public void getKLatestMessagesBeforeMIndividual() {
        createIndividualMessage = new CreateIndividualMessage(individualConversationStorage);
        InputCreateIndividualMessage inputIndividualMessage = 
        new InputCreateIndividualMessage(usingUser.getID(), usingUser.getID(), idIndividualConversation, "bbbbbb");
        OutputCreateIndividualMessage outputCreateGroupMessage = createIndividualMessage.excute(inputIndividualMessage);

        GetKLatestMessagesBeforeMIndividual getKLatestMessagesBeforeMIndividual =
        new GetKLatestMessagesBeforeMIndividual(individualConversationStorage);
        InputGetKLatestMessagesBeforeMIndividual inputGetKLatestMessagesBeforeMIndividual =
        new InputGetKLatestMessagesBeforeMIndividual(idIndividualConversation, 1, 0);
        OutputGetKLatestMessagesBeforeMIndividual outputGetKLatestMessagesBeforeMIndividual =
        getKLatestMessagesBeforeMIndividual.excute(inputGetKLatestMessagesBeforeMIndividual);
        assertEquals(outputGetKLatestMessagesBeforeMIndividual.getResult(), GetKLatestMessagesBeforeMIndividualResult.Successed);
    }

    @Test 
    public void getLatestReadMessageGroup() {
        PrivateGroup _privateGroup = privateGroupStorage.getPrivateGroup().getById(idPrivateGroup);
        GroupConversation conversation = groupConversationStorage.getConversation().getById(_privateGroup.getIdConversation());
        createGroupMessage = new CreateGroupMessage(groupConversationStorage);
        InputCreateGroupMessage inputCreateGroupMessage = 
        new InputCreateGroupMessage(idPrivateGroup, usingUser.getID(), conversation.getID(), "Aaaaaaa");
        OutputCreateGroupMessage outputCreateGroupMessage = createGroupMessage.excute(inputCreateGroupMessage);

        GetLatestReadMessageGroup getLatestReadMessageGroup = 
        new GetLatestReadMessageGroup(groupConversationStorage);
        InputGetLatestReadMessageGroup inputGetLatestReadMessageGroup =
        new InputGetLatestReadMessageGroup(conversation.getID(), usingUser.getID());
        OutputGetLatestReadMessageGroup outputGetLatestReadMessageGroup =
        getLatestReadMessageGroup.excute(inputGetLatestReadMessageGroup);
        assertEquals(outputGetLatestReadMessageGroup.getResult(), GetLatestReadMessageGroupResult.Failed);
    }
}
