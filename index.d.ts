declare module '@apiverve/cardvalidator' {
  export interface cardvalidatorOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface cardvalidatorResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class cardvalidatorWrapper {
    constructor(options: cardvalidatorOptions);

    execute(callback: (error: any, data: cardvalidatorResponse | null) => void): Promise<cardvalidatorResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: cardvalidatorResponse | null) => void): Promise<cardvalidatorResponse>;
    execute(query?: Record<string, any>): Promise<cardvalidatorResponse>;
  }
}
