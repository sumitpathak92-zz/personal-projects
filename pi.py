 # -*- coding: utf-8 -*-class AlpsProjectThemeDownloadAPIView(AlpsProjectDashboardAPIView):
    """
    Author: Rohan Agarwal
    Reviewed by: Aashish Kumar
    """

    def get(self, request, tenant_code, project_id):
        #Check session validation
        session_obj = AlpsSession(request, tenant_code)
        session_response = session_obj.validate_session()
        if not session_response['valid']:
            return session_response['response']

        def get_value_string(x):
            try:
                return format_number(x)
            except:
                return x

        format_number = lambda x, d=0: int(round(float(x))) if d == 0 else round(float(x), d)
        fillna = lambda x, r='-': r if (str(x).upper() in ['NA', ''] or x is None) else get_value_string(x)
        try:
            import pdb;pdb.set_trace()
            api_obj = AlpsProjectThemeTrafficAPIView()

            api_response = api_obj.get(request, tenant_code, project_id).data
            input_response = api_response['data']
            filter_dict = api_obj.filter
            print filter_dict

            filtered_month = filter_dict.get('month', datetime.now().month)
            filtered_year = filter_dict.get('year', datetime.now().year)
            if filtered_month == datetime.now().month and filtered_year == datetime.now().year:
                filtered_date = datetime.now().day
            else:
                next_month_date = datetime.strptime(str(filtered_year) + '-' + str(filtered_month) + '-25',
                                                    '%Y-%m-%d') + timedelta(days=10)
                filtered_date = (datetime.strptime(str(next_month_date.year) + '-' + str(next_month_date.month) + '-01',
                                                   '%Y-%m-%d') - timedelta(days=1)).day

            last_day_month = datetime.strptime(
                str(filtered_year) + '-' + str(filtered_month) + '-' + str(filtered_date), '%Y-%m-%d')
            first_day_month = datetime.strptime(str(last_day_month.year) + '-' + str(last_day_month.month) + '-01',
                                                '%Y-%m-%d')
            last_day_last_month = datetime.strptime(str(last_day_month.year) + '-' + str(last_day_month.month) + '-01',
                                                    '%Y-%m-%d') - timedelta(days=1)
            first_day_last_month = datetime.strptime(
                str(last_day_last_month.year) + '-' + str(last_day_last_month.month) + '-01', '%Y-%m-%d')

            filtered_search_engines = ' | '.join(item.title() for item in filter_dict['search_engines'])

            filtered_business_priority = filter_dict.get('priority__in', '-')
            if filtered_business_priority != '-':
                filtered_business_priority = ' | '.join('Priority ' + str(item) for item in filtered_business_priority)

            filtered_keyword_groups = filter_dict.get('keyword_type__in', '-')
            if filtered_keyword_groups != '-':
                filtered_keyword_groups = ' | '.join(
                    str(item).title().replace('_', ' ') for item in filtered_keyword_groups)

            request_dict = dict(request.GET)
            source=str(request_dict['traffic_sources'][0])
            if source == 'estimated_traffic':
                source = 'Estimated'
            else:
                source = 'Google Search Console'
            filtered_keyword_theme_ids = request_dict.get('themes', '-')
            if filtered_keyword_theme_ids != '-':
                filtered_keyword_themes = []
                project_themes_data = AlpsProjectTheme.objects.filter(project_id=project_id,
                                                                      tenant_code=tenant_code)
                for project_theme in project_themes_data:
                    if str(
                            project_theme.id) in filtered_keyword_theme_ids and project_theme.theme not in filtered_keyword_themes:
                        filtered_keyword_themes.append(project_theme.theme)
                filtered_keyword_themes = ' | '.join(str(item) for item in filtered_keyword_themes)
            else:
                filtered_keyword_themes = '-'

            filtered_search_intent_ids = request_dict.get('purchase_cycles', '-')
            if filtered_search_intent_ids != '-':
                filtered_search_intent = []
                project_purchase_cycle_data = AlpsProjectPurchaseCycle.objects.filter(tenant_code=tenant_code,
                                                                                      project_id=project_id)
                for project_purchase_cycle in project_purchase_cycle_data:
                    if str(
                            project_purchase_cycle.id) in filtered_search_intent_ids and project_purchase_cycle.purchase_cycle not in filtered_search_intent:
                        filtered_search_intent.append(project_purchase_cycle.purchase_cycle)
                filtered_search_intent = ' | '.join(str(item) for item in filtered_search_intent)
            else:
                filtered_search_intent = '-'

            response = HttpResponse(content_type='text/csv', status=200)
            writer = csv.writer(response)

            writer.writerow(['Filter Selection'])
            writer.writerow(['Current Date Range',
                             format(first_day_month, "%d %b '%y") + ' - ' + format(last_day_month, "%d %b '%y")])
            writer.writerow(['Previous Date Range',
                             format(first_day_last_month, "%d %b '%y") + ' - ' + format(last_day_last_month,
                                                                                        "%d %b '%y")])
            writer.writerow(['Search Engine', filtered_search_engines])
            writer.writerow(['Business Priority', filtered_business_priority])
            writer.writerow(['Keyword Groups', filtered_keyword_groups])
            writer.writerow(['Keyword Themes', filtered_keyword_themes])
            writer.writerow(['Search Intent', filtered_search_intent])
            writer.writerow([])

            try:
                business_competitors = input_response[0]['business_competitors']
                business_competitors_top4 = sorted(business_competitors, key=lambda k: k['rank'])[:4]
                competitor_heading = []
                for each_competitor in business_competitors_top4:
                    competitor_heading.append(each_competitor['alias_name'].title() + ' Current Avg. Rank')
                    competitor_heading.append(each_competitor['alias_name'].title() + ' Previous Avg. Rank')
            except:
                competitor_heading = ['Comp 1 Current Avg. Rank', 'Comp 1 Previous Avg. Rank',
                                      'Comp 2 Current Avg. Rank', 'Comp 2 Previous Avg. Rank',
                                      'Comp 3 Current Avg. Rank', 'Comp 3 Previous Avg. Rank',
                                      'Comp 4 Current Avg. Rank', 'Comp 4 Previous Avg. Rank',
                                      ]

            heading = ['Theme', 'Target URL', 'Search Volume', 'Business Priority', 'Search Intent',
                       'Current Avg. Rank', 'Previous Avg. Rank', 'Keyword Count'] \
                      + competitor_heading + \
                      ['Current %s Traffic' %source, 'Previous %s Traffic' %source, 'Current %s Conversions' %source,
                         'Previous %s Conversions' %source,'Current %s Sales Completions' %source,
                         'Previous %s Sales Completions' %source]
            writer.writerow(heading)

            for theme_data in input_response:
                row_data = []
                row_data.append(fillna(theme_data['theme_name']))
                row_data.append(fillna(theme_data['preferred_url']))
                if theme_data['search_volume'] == 'NP' or theme_data['search_volume'] == None:
                    theme_data['search_volume']='-'
                    row_data.append(fillna(theme_data['search_volume']))
                else:
                    row_data.append(fillna(theme_data['search_volume']))
                row_data.append(fillna(theme_data['business_priority']))
                row_data.append(fillna(theme_data['search_intent']))
                if theme_data['rank'] == 'NP' or theme_data['rank'] == None:
                    theme_data['rank']='-'
                    row_data.append(fillna(theme_data['rank']))
                else:
                    row_data.append(fillna(theme_data['rank']))
                if theme_data['rank_prev'] == 'NP' or theme_data['rank_prev'] == None:
                    theme_data['rank_prev']='-'
                    row_data.append(fillna(theme_data['rank_prev']))
                else:
                    row_data.append(fillna(theme_data['rank_prev']))
                row_data.append(fillna(theme_data['keyword_count']))

                business_competitors = theme_data['business_competitors']
                business_competitors_top4 = sorted(business_competitors, key=lambda k: k['rank'])[:4]
                for each_competitor in business_competitors_top4:
                    if each_competitor['rank'] == 'NP' or each_competitor['rank'] == None:
                        each_competitor['rank']='-'
                        row_data.append(fillna(each_competitor['rank']))
                    else:
                        row_data.append(fillna(each_competitor['rank']))
                    if each_competitor['rank_prev'] == 'NP' or each_competitor['rank_prev'] == None:
                        each_competitor['rank_prev']='-'
                        row_data.append(fillna(each_competitor['rank_prev']))
                    else:
                        row_data.append(fillna(each_competitor['rank_prev']))
                if theme_data['keyword_count'] == 0:
                    theme_data['traffic'] = '-'
                if theme_data['traffic'] == 'NP' or theme_data['traffic'] == None:
                    theme_data['traffic']='-'
                    row_data.append(fillna(theme_data['traffic']))
                else:
                    row_data.append(fillna(theme_data['traffic']))
                if theme_data['traffic_prev'] == 'NP' or theme_data['traffic_prev'] == None:
                    theme_data['traffic_prev']='-'
                    row_data.append(fillna(theme_data['traffic_prev']))
                else:
                    row_data.append(fillna(theme_data['traffic_prev']))
                if theme_data['conversion'] == 'NP' or theme_data['conversion'] == None:
                    theme_data['conversion']='-'
                    row_data.append(fillna(theme_data['conversion']))
                else:
                    row_data.append(fillna(theme_data['conversion']))
                if theme_data['conversion_prev'] == 'NP' or theme_data['conversion_prev'] == None:
                    theme_data['conversion_prev']='-'
                    row_data.append(fillna(theme_data['conversion_prev']))
                else:
                    row_data.append(fillna(theme_data['conversion_prev']))
                if theme_data['sales'] == 'NP' or theme_data['sales'] == None:
                    theme_data['sales']='-'
                    row_data.append(fillna(theme_data['sales']))
                else:
                    row_data.append(fillna(theme_data['sales']))
                if theme_data['sales_prev'] == 'NP' or theme_data['sales_prev'] == None:
                    theme_data['sales_prev']='-'
                    row_data.append(fillna(theme_data['sales_prev']))
                else:
                    row_data.append(fillna(theme_data['sales_prev']))
                writer.writerow(row_data)

            return session_obj.prepare_download_response(response)

        except Exception as e:
            return session_obj.prepare_api_response(e, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

