package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.ReportModel;
import com.liferay.CommunityApp.repositories.CommentRepository;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import com.liferay.CommunityApp.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    ReportRepository reportRepository;


    public List<ReportModel> CommunityReport() {
        List<CommunityModel> communities = communityRepository.findAll();
        List<ReportModel> reportList = new ArrayList<>();

        for (CommunityModel community : communities) {
            ReportModel report = new ReportModel();
            report.setId(UUID.randomUUID());
            report.setCommunityName(community.getName());
            report.setPrivateCommunity(community.getParticular());
            report.setNumberOfMembers(community.getMembers().size());
            report.setReportUpdateDate(LocalDateTime.now());
//            report.setNumberOfPosts(postRepository.countByCommunity(community));
//            report.setNumberOfComments(commentRepository.countByPost_Community(community));

            reportList.add(report);
        }
        return reportRepository.saveAll(reportList);
    }
}

